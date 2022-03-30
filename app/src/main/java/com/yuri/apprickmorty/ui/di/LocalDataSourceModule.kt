package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.data.db.PersonagemDao
import com.yuri.apprickmorty.data.repositories.local.PersonagemLocalDataSource
import com.yuri.apprickmorty.data.repositories.local.PersonagemLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataSourceModule {

    @Singleton
    @Provides
    fun providePersonagemLocalDatasource(
        personagemDao: PersonagemDao
    ): PersonagemLocalDataSource {
        return PersonagemLocalDataSourceImpl(personagemDao)
    }
}