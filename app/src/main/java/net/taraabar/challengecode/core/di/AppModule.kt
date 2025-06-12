package net.taraabar.challengecode.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.taraabar.challengecode.ui.navigation.ITaraabarNavigation
import net.taraabar.challengecode.ui.navigation.TaraabarNavigation
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideTaraabarNavigation(iNavControllerProvider: ITaraabarNavigation): ITaraabarNavigation =
        TaraabarNavigation(iNavControllerProvider)
}