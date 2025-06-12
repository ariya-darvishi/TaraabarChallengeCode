package net.taraabar.network.di

import okhttp3.Authenticator
import okhttp3.Interceptor

interface IAuthRelatedInterceptorsProvider {
    fun getAuthInterceptors(): Set<Interceptor>
    fun getAuthenticator(): Authenticator?
}