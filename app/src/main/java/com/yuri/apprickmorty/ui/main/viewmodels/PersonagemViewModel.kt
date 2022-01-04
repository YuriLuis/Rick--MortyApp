package com.yuri.apprickmorty.ui.main.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.repositories.PersonagemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonagemViewModel(
    private val repository: PersonagemRepository
): ViewModel() {

    private var personagens = MutableLiveData<List<Personagem>>()

    val personagensLiveData: LiveData<List<Personagem>>
    get() = personagens


    fun getPersonagens(pagina: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = repository.getPersonagens(pagina)
            personagens.postValue(listaPersonagens.data?.results)
        }
    }
}