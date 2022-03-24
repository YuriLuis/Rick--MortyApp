package com.yuri.apprickmorty.ui.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuri.apprickmorty.domain.usescases.*
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModel

class PersonagemViewModelFactory(
    private val getAllPersonagensUseCase: GetAllPersonagensUseCase,
    private val getPersonagensPorNomeUseCase: GetPersonagensPorNomeUseCase,
    private val getPersonagensPorStatusEGeneroUseCase: GetPersonagensPorStatusEGeneroUseCase,
    private val getPersonagensPorGeneroUseCase: GetPersonagensPorGeneroUseCase,
    private val getPersonagensPorStatusUseCase: GetPersonagensPorStatusUseCase,
    private val savePersogensFavoritosUseCase: SavePersogensFavoritosUseCase,
    private val deletePersonansFavoritosUseCase: DeletePersonansFavoritosUseCase,
    private val getAllPersonagensFavoritosUseCase: GetAllPersonagensFavoritosUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonagemViewModel(
            getAllPersonagensUseCase,
            getPersonagensPorNomeUseCase,
            getPersonagensPorStatusEGeneroUseCase,
            getPersonagensPorGeneroUseCase,
            getPersonagensPorStatusUseCase,
            savePersogensFavoritosUseCase,
            deletePersonansFavoritosUseCase,
            getAllPersonagensFavoritosUseCase
        ) as T
    }
}