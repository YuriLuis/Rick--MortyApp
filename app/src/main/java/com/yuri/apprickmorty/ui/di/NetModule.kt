package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.BuildConfig
import com.yuri.apprickmorty.data.api.RickMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

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
    fun provideRickMortyApiService(retrofit: Retrofit): RickMortyApi {
        return retrofit.create(RickMortyApi::class.java)
    }
}