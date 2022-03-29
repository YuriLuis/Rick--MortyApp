package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.data.repositories.local.PersonagemLocalDataSource
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.domain.repository.PersonagemRepository
import com.yuri.apprickmorty.domain.repository.PersonagemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePersonagemRepository(
        remoteDataSource: PersonagemRemoteDataSource,
        localDataSource: PersonagemLocalDataSource
    ): PersonagemRepository {
        return PersonagemRepositoryImpl(remoteDataSource, localDataSource)
    }
}