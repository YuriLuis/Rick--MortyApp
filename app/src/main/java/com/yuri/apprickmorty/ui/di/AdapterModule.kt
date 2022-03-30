package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.ui.adapters.ListaPersonagemAdapter
import com.yuri.apprickmorty.ui.adapters.ListaPersonagemAdapterPaging
import com.yuri.apprickmorty.ui.adapters.ListaPersonagensFavoritosAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providePersonagemAdapter(): ListaPersonagemAdapter {
        return ListaPersonagemAdapter()
    }

    @Singleton
    @Provides
    fun providePersonagemAdapterPaging(): ListaPersonagemAdapterPaging {
        return ListaPersonagemAdapterPaging()
    }

    @Singleton
    @Provides
    fun providePersonagemFavoritosAdapter(): ListaPersonagensFavoritosAdapter {
        return ListaPersonagensFavoritosAdapter()
    }
}