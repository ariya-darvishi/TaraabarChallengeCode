package net.taraabar.network.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.taraabar.network.BuildConfig
import net.taraabar.network.di.models.Retrofits
import net.taraabar.network.repository.INetworkState
import net.taraabar.network.repository.NetworkState
import okhttp3.Cache
import okhttp3.CertificatePinner
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder()
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()


    @Singleton
    @Provides
    fun okhttp(
        authInterceptorsProviders: Set<@JvmSuppressWildcards IAuthRelatedInterceptorsProvider>,
        logger: HttpLoggingInterceptor,
    ): OkHttpClient {
        val ret = OkHttpClient.Builder()
            .connectionSpecs(
                listOf(
                    ConnectionSpec.CLEARTEXT,
                    ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .allEnabledTlsVersions()
                        .allEnabledCipherSuites()
                        .build()
                )
            )
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        authInterceptorsProviders.forEach { authInterceptorsProvider ->
            authInterceptorsProvider.getAuthenticator()?.let {
                ret.authenticator(it)
            }
            authInterceptorsProvider.getAuthInterceptors().forEach {
                ret.addInterceptor(it)
            }
        }

        ret.addInterceptor(logger)
        ret.certificatePinner(
            CertificatePinner.Builder()
                .add(
                    "www.taraabar.net",
                    "sha256/8f..."
                )
                .build()
        )
        return ret.build()
    }

    @Singleton
    @Provides
    fun okHttpCache(@ApplicationContext context: Context): Cache =
        Cache(context.cacheDir, 10 * 1024 * 1024)

    @Singleton
    @Provides
    fun loggerInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }


    @Singleton
    @Provides
    fun retrofits(
        okHttpClient: OkHttpClient,
        moshi: Moshi,
        urlProviders: Set<@JvmSuppressWildcards IURLProvider>
    ): Retrofits {
        val urlList = urlProviders.flatMap { provider -> provider.getURLS() }.toSet()
        if (urlList.isEmpty()) {
            throw Exception("چرا آدرس تامین نکردی؟؟؟؟؟؟؟")
        }
        val map = mutableMapOf<String, Retrofit>()
        urlList.forEach {
            map[it.serviceName] = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .baseUrl(it.url)
                .build()
        }
        return Retrofits(services = map)
    }

    @Singleton
    @Provides
    fun networkUtils(@ApplicationContext context: Context): INetworkState = NetworkState(context)

}