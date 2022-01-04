package com.yuri.apprickmorty.ui.main.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuri.apprickmorty.data.repositories.PersonagemRepository
import com.yuri.apprickmorty.ui.main.viewmodels.PersonagemViewModel

class PersonagemViewModelFactory(
    private val repository: PersonagemRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonagemViewModel(repository) as T
    }
}