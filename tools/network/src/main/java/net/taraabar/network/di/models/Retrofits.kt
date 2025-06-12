package net.taraabar.network.di.models

import retrofit2.Retrofit

data class Retrofits(
    val services: Map<String, Retrofit>
)
