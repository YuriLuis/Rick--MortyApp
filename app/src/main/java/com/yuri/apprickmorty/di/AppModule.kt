package com.yuri.apprickmorty.di

import android.app.Application
import com.yuri.apprickmorty.BuildConfig
import com.yuri.apprickmorty.data.repositories.PersonagemRepository
import com.yuri.apprickmorty.data.repositories.PersonagemRepositoryImpl
import com.yuri.apprickmorty.data.services.remote.api.RickMortyApi
import com.yuri.apprickmorty.ui.main.adapters.ListaPersonagemAdapter
import com.yuri.apprickmorty.ui.main.viewmodelsfactory.PersonagemViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providePersonagemAdapter(): ListaPersonagemAdapter {
        return ListaPersonagemAdapter()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.RICK_MORTY_API_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RickMortyApi {
        return retrofit.create(RickMortyApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDatasource(rickMortyApi: RickMortyApi): PersonagemRepository {
        return PersonagemRepositoryImpl(rickMortyApi)
    }

    @Singleton
    @Provides
    fun providePersonagemViewModelFactory(repository: PersonagemRepository): PersonagemViewModelFactory {
        return PersonagemViewModelFactory(repository = repository)
    }
}