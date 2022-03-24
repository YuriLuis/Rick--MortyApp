package com.yuri.apprickmorty.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yuri.apprickmorty.data.models.Personagem

@Database(
    entities = [Personagem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PersonagemDatabase : RoomDatabase() {
    abstract fun getPersonagemDao(): PersonagemDao
}