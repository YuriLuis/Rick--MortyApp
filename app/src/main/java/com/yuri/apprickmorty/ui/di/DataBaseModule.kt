package com.yuri.apprickmorty.ui.di

import android.app.Application
import androidx.room.Room
import com.yuri.apprickmorty.data.db.PersonagemDao
import com.yuri.apprickmorty.data.db.PersonagemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun providePersonagemDataBase(app: Application): PersonagemDatabase {
        return Room.databaseBuilder(app, PersonagemDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePersonagemDao(articleDataBase: PersonagemDatabase): PersonagemDao {
        return articleDataBase.getPersonagemDao()
    }
}