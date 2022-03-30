package com.yuri.apprickmorty.ui.viewmodelsfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.ui.viewmodels.PersonagemViewModelPaging

class PersonagemViewModelPagingFactory(
    private val remoteDataSource: PersonagemRemoteDataSource
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PersonagemViewModelPaging(remoteDataSource) as T
    }


}