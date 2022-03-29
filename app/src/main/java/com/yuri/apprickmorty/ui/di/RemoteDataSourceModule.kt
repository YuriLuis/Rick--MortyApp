package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.data.api.RickMortyApi
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Singleton
    @Provides
    fun providePersonagemRemoteDatasource(api: RickMortyApi): PersonagemRemoteDataSource {
        return PersonagemRemoteDataSourceImpl(api)
    }
}