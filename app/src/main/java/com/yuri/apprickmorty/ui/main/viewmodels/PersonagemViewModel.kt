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
    private var isFiltro = MutableLiveData<Boolean>()

    val personagensLiveData: LiveData<List<Personagem>>
    get() = personagens

    val isFiltroLiveData: LiveData<Boolean>
    get() = isFiltro

    fun getPersonagens(pagina: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = repository.getPersonagens(pagina)
            personagens.postValue(listaPersonagens.data?.results)
            isFiltro.postValue(false)
        }
    }

    fun getPersonagensPorNome(nome: String){
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = repository.getPersonagensPorNome(nome)
            personagens.postValue(listaPersonagens.data?.results)
            isFiltro.postValue(true)
        }
    }

    fun getPersonagensPorStatusEGenero(status: String, genero: String, pagina: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = repository.getPersonagensPorStatusEGenero(status, genero, pagina)
            personagens.postValue(listaPersonagens.data?.results)
            isFiltro.postValue(true)
        }
    }

    fun getPersonagensPorGenero(genero: String, pagina: Int){
        viewModelScope.launch(Dispatchers.IO){
            val listaPersonagens = repository.getPersonagensPorGenero(genero,pagina)
            personagens.postValue(listaPersonagens.data?.results)
            isFiltro.postValue(true)
        }
    }

    fun getPersonagensPorStatus(status: String, pagina: Int){
        viewModelScope.launch(Dispatchers.IO){
            val listaPersonagens = repository.getPersonagensPorStatus(status, pagina)
            personagens.postValue(listaPersonagens.data?.results)
            isFiltro.postValue(true)
        }
    }
}