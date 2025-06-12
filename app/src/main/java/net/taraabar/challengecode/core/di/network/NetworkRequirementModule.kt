package net.taraabar.challengecode.core.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import net.taraabar.challengecode.BuildConfig
import net.taraabar.network.di.IAuthRelatedInterceptorsProvider
import net.taraabar.network.di.IURLProvider
import net.taraabar.network.di.models.BaseUrlModel
import okhttp3.Authenticator
import okhttp3.Interceptor
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkRequirementModule {

    @Singleton
    @Provides
    @IntoSet
    fun provideInterceptors(): IAuthRelatedInterceptorsProvider =
        object : IAuthRelatedInterceptorsProvider {
            override fun getAuthInterceptors(): Set<Interceptor> = emptySet()
            override fun getAuthenticator(): Authenticator? = null
        }

    @Singleton
    @Provides
    @IntoSet
    fun provideUrls(): IURLProvider = object : IURLProvider {
        override fun getURLS(): Set<BaseUrlModel> = setOf(
            BaseUrlModel(
                serviceName = BuildConfig.TaraabarService,
                url = BuildConfig.TaraabarServiceURL
            )
        )

    }
}