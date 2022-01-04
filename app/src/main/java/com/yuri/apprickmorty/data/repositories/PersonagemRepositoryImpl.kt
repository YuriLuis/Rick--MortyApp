package com.yuri.apprickmorty.data.repositories

import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.services.remote.api.RickMortyApi
import com.yuri.apprickmorty.utils.Resource
import retrofit2.Response

class PersonagemRepositoryImpl(
    private var api: RickMortyApi
): PersonagemRepository {


    private fun responseToResource(response: Response<PersonagemResponse>) : Resource<PersonagemResponse>{
        if (response.isSuccessful){
            response.body()?.let {result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }


    override suspend fun getPersonagens(pagina: Int): Resource<PersonagemResponse> {
        return responseToResource(response = api.getPersonagens(pagina))
    }
}