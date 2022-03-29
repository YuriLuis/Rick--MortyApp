package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.domain.usescases.*
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModelPaging
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelFactory
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelPagingFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providePersonagemViewModelFactory(
        getAllPersonagensUseCase: GetAllPersonagensUseCase,
        getPersonagensPorNomeUseCase: GetPersonagensPorNomeUseCase,
        getPersonagensPorStatusEGeneroUseCase: GetPersonagensPorStatusEGeneroUseCase,
        getPersonagensPorGeneroUseCase: GetPersonagensPorGeneroUseCase,
        getPersonagensPorStatusUseCase: GetPersonagensPorStatusUseCase,
        savePersogensFavoritosUseCase: SavePersogensFavoritosUseCase,
        deletePersonansFavoritosUseCase: DeletePersonansFavoritosUseCase,
        getAllPersonagensFavoritosUseCase: GetAllPersonagensFavoritosUseCase
    ): PersonagemViewModelFactory {
        return PersonagemViewModelFactory(
            getAllPersonagensUseCase,
            getPersonagensPorNomeUseCase,
            getPersonagensPorStatusEGeneroUseCase,
            getPersonagensPorGeneroUseCase,
            getPersonagensPorStatusUseCase,
            savePersogensFavoritosUseCase,
            deletePersonansFavoritosUseCase,
            getAllPersonagensFavoritosUseCase
        )
    }

    @Singleton
    @Provides
    fun providePersonagemViewModelPagingFactory(
        remoteDataSource: PersonagemRemoteDataSource
    ): PersonagemViewModelPagingFactory {
        return PersonagemViewModelPagingFactory(
            remoteDataSource
        )
    }
}