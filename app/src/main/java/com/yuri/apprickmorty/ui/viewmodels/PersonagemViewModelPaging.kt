package com.yuri.apprickmorty.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.ui.paging.PersonagemPagingSource
import kotlinx.coroutines.flow.Flow

class PersonagemViewModelPaging(
    private val remoteDataSource: PersonagemRemoteDataSource
) : ViewModel() {

    private var isCarregando = MutableLiveData<Boolean>()

    val isCarregandoLiveData: LiveData<Boolean>
        get() = isCarregando

    fun getListData(): Flow<PagingData<Personagem>> {

        isCarregando.postValue(true)

        var pager = Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { PersonagemPagingSource(remoteDataSource) }).flow.cachedIn(
            viewModelScope
        )

        isCarregando.postValue(false)

        return pager
    }
}