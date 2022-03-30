package com.yuri.apprickmorty.ui.di

import com.yuri.apprickmorty.domain.repository.PersonagemRepository
import com.yuri.apprickmorty.domain.usescases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun providesGetAllPersonagensUseCase(personagemRepository: PersonagemRepository): GetAllPersonagensUseCase {
        return GetAllPersonagensUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorNomeUseCase(personagemRepository: PersonagemRepository): GetPersonagensPorNomeUseCase {
        return GetPersonagensPorNomeUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorStatusEGenero(personagemRepository: PersonagemRepository): GetPersonagensPorStatusEGeneroUseCase {
        return GetPersonagensPorStatusEGeneroUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorGeneroUseCase(personagemRepository: PersonagemRepository): GetPersonagensPorGeneroUseCase {
        return GetPersonagensPorGeneroUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorStatusUseCase(personagemRepository: PersonagemRepository): GetPersonagensPorStatusUseCase {
        return GetPersonagensPorStatusUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesSavePersonagensFavoritosUseCase(personagemRepository: PersonagemRepository): SavePersogensFavoritosUseCase {
        return SavePersogensFavoritosUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesDeletePersonagensFavoritosUseCase(personagemRepository: PersonagemRepository): DeletePersonansFavoritosUseCase {
        return DeletePersonansFavoritosUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetAllPersonagensFavoritosUseCase(personagemRepository: PersonagemRepository): GetAllPersonagensFavoritosUseCase {
        return GetAllPersonagensFavoritosUseCase(personagemRepository)
    }
}