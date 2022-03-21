package com.yuri.apprickmorty.data.repositories.local

import androidx.lifecycle.LiveData
import com.yuri.apprickmorty.data.models.Personagem

interface PersonagemLocalDataSource {

    suspend fun savePersonagemRoomDatabase(personagem: Personagem)

    suspend fun deletePersonagemRoomDatabase(personagem: Personagem)

    fun getAllPersonagensRoomDatabase(): LiveData<List<Personagem>>
}