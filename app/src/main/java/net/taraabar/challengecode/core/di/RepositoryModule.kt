package net.taraabar.challengecode.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.taraabar.challengecode.data.repository.ITaraabarRepository
import net.taraabar.challengecode.data.repository.TaraabarRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {


    @Singleton
    @Binds
    fun providesTaraabarRepository(binder: TaraabarRepository): ITaraabarRepository


}