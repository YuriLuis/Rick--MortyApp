package com.yuri.apprickmorty.data.repositories.local

import androidx.lifecycle.LiveData
import com.yuri.apprickmorty.data.db.PersonagemDao
import com.yuri.apprickmorty.data.models.Personagem

class PersonagemLocalDataSourceImpl(
    private val personagemDao: PersonagemDao
): PersonagemLocalDataSource {

    override suspend fun savePersonagemRoomDatabase(personagem: Personagem) {
        personagemDao.insertPersonagem(personagem)
    }

    override suspend fun deletePersonagemRoomDatabase(personagem: Personagem) {
        personagemDao.deletePersonagem(personagem)
    }

    override fun getAllPersonagensRoomDatabase(): LiveData<List<Personagem>> {
        return personagemDao.getAllPersonagensFavoritos()
    }
}