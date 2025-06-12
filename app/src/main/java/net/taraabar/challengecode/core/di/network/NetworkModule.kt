package net.taraabar.challengecode.core.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.taraabar.challengecode.BuildConfig
import net.taraabar.challengecode.data.remote.service.TaraabarService
import net.taraabar.network.di.models.Retrofits
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun taraabarService(retrofits: Retrofits): TaraabarService =
        retrofits.services[BuildConfig.TaraabarService]?.create(TaraabarService::class.java)
            ?: throw RuntimeException("سرویسی ایجاد نشده است! لطفا سرویس مورد نظر را ایجاد نمایید.")

}