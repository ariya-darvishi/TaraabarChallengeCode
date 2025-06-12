package net.taraabar.network.repository

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import net.taraabar.network.di.models.ConnectionState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkState @Inject constructor(
    @ApplicationContext context: Context
) : INetworkState {

    private val cm = context.getSystemService(ConnectivityManager::class.java)

    private fun getCurrentConnectivityState(): ConnectionState {
        val connected = cm.allNetworks.any { network ->
            cm.getNetworkCapabilities(network)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        }

        return if (connected) ConnectionState.Available else ConnectionState.Unavailable
    }


    private fun observeConnectivity() = callbackFlow {
        val callback = getNetWorkCallback { connectionState -> trySend(connectionState) }
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .removeCapability(NetworkCapabilities.NET_CAPABILITY_NOT_VPN)
            .build()

        cm.registerNetworkCallback(networkRequest, callback)

        trySend(getCurrentConnectivityState())
        awaitClose {
            cm.unregisterNetworkCallback(callback)
        }
    }

    private fun getNetWorkCallback(callback: (ConnectionState) -> Unit): NetworkCallback {
        return object : NetworkCallback() {
            @SuppressLint("SuspiciousIndentation")
            override fun onAvailable(network: Network) {

                val activeNetwork = cm.activeNetwork
                val nc = cm.getNetworkCapabilities(activeNetwork)
                val isVpnConnected = nc?.hasTransport(NetworkCapabilities.TRANSPORT_VPN) ?: false
                val isProxySet = cm.defaultProxy
                if (isVpnConnected || isProxySet != null)
                    callback(ConnectionState.VPNIsConnected)
                else
                    callback(ConnectionState.Available)
            }

            override fun onLost(network: Network) {

                callback(ConnectionState.Unavailable)
            }
        }
    }


    override fun connectionStateFlow(): Flow<ConnectionState> =
        observeConnectivity().flowOn(Dispatchers.IO)
}