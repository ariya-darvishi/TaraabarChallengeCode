package net.taraabar.network.di.models

sealed class ConnectionState {
    data object Available : ConnectionState()
    data object Unavailable : ConnectionState()
    data object VPNIsConnected : ConnectionState()
}
