package com.yuri.apprickmorty.data.services.remote.api

import com.yuri.apprickmorty.data.models.PersonagemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {

    @GET("api/character")
    suspend fun getPersonagens(@Query("page") page: Int) : Response<PersonagemResponse>
}