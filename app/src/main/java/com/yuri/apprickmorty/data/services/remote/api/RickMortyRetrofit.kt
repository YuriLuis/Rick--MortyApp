package com.yuri.apprickmorty.data.services.remote.api

import com.yuri.apprickmorty.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RickMortyRetrofit {
    private var interceptor = HttpLoggingInterceptor()

    private val retrofit by lazy{
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        var client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        Retrofit.Builder()
            .baseUrl(BuildConfig.RICK_MORTY_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiRickMorty : RickMortyApi by lazy {
        retrofit.create(RickMortyApi::class.java)
    }
}