package net.taraabar.network.repository

import net.taraabar.network.di.models.ConnectionState
import kotlinx.coroutines.flow.Flow

interface INetworkState {
    fun connectionStateFlow(): Flow<ConnectionState>
}