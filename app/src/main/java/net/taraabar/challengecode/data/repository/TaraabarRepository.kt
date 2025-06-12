package net.taraabar.challengecode.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import net.taraabar.challengecode.data.remote.model.response.CargoResponse
import net.taraabar.challengecode.data.remote.service.TaraabarDataSource
import net.taraabar.network.base.CustomResult
import javax.inject.Inject

class TaraabarRepository @Inject constructor(
    private val dataSource: TaraabarDataSource,
    @ApplicationContext private val context: Context,
) : ITaraabarRepository {

    override suspend fun acceptCargo(): Flow<CustomResult<Boolean>> =
        flow {
            emit(dataSource.acceptCargo())
        }.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)

    override suspend fun getMockCargoList(): Flow<CustomResult<List<CargoResponse>>> =
        flow {
            emit(dataSource.getMockCargoList())
        }.onStart {
            emit(CustomResult.Loading)
        }.flowOn(Dispatchers.IO)
}

