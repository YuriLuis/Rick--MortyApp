package com.yuri.apprickmorty.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yuri.apprickmorty.data.models.Personagem

const val PERSONAGENS = "personagens"

@Dao
interface PersonagemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPersonagem(personagem: Personagem)

    @Query("SELECT * FROM $PERSONAGENS")
    fun getAllPersonagensFavoritos(): LiveData<List<Personagem>>

    @Delete
    suspend fun deletePersonagem(personagem: Personagem)
}