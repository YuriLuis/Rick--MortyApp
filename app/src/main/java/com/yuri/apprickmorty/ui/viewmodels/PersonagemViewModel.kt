package com.yuri.apprickmorty.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.utils.Resource
import com.yuri.apprickmorty.domain.usescases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonagemViewModel(
    private val getAllPersonagensUseCase: GetAllPersonagensUseCase,
    private val getPersonagensPorNomeUseCase: GetPersonagensPorNomeUseCase,
    private val getPersonagensPorStatusEGeneroUseCase: GetPersonagensPorStatusEGeneroUseCase,
    private val getPersonagensPorGeneroUseCase: GetPersonagensPorGeneroUseCase,
    private val getPersonagensPorStatusUseCase: GetPersonagensPorStatusUseCase,
    private val savePersogensFavoritosUseCase: SavePersogensFavoritosUseCase,
    private val deletePersonansFavoritosUseCase: DeletePersonansFavoritosUseCase,
    private val getAllPersonagensFavoritosUseCase: GetAllPersonagensFavoritosUseCase
) : ViewModel() {

    private var personagens: MutableLiveData<Resource<PersonagemResponse>> = MutableLiveData()
    private var isFiltro = MutableLiveData<Boolean>()
    private var isCarregando = MutableLiveData<Boolean>()

    val personagensLiveData: LiveData<Resource<PersonagemResponse>>
        get() = personagens

    val isFiltroLiveData: LiveData<Boolean>
        get() = isFiltro

    val isCarregandoLiveData: LiveData<Boolean>
        get() = isCarregando

    fun getPersonagens(pagina: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isCarregando.postValue(true)
            val listaPersonagens = getAllPersonagensUseCase.execute(pagina)
            personagens.postValue(listaPersonagens)
            isFiltro.postValue(false)
            isCarregando.postValue(false)
        }
    }

    fun getPersonagensPorNome(nome: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = getPersonagensPorNomeUseCase.execute(nome)
            personagens.postValue(listaPersonagens)
            isFiltro.postValue(true)
        }
    }

    fun getPersonagensPorStatusEGenero(status: String, genero: String, pagina: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = getPersonagensPorStatusEGeneroUseCase.execute(
                status, genero, pagina
            )
            personagens.postValue(listaPersonagens)
            isFiltro.postValue(true)
        }
    }

    fun getPersonagensPorGenero(genero: String, pagina: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = getPersonagensPorGeneroUseCase.execute(genero,pagina)
            personagens.postValue(listaPersonagens)
            isFiltro.postValue(true)
        }
    }

    fun getPersonagensPorStatus(status: String, pagina: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val listaPersonagens = getPersonagensPorStatusUseCase.execute(status,pagina)
            personagens.postValue(listaPersonagens)
            isFiltro.postValue(true)
        }
    }

    fun savePersonagemFavorito(personagem: Personagem){
        viewModelScope.launch(Dispatchers.IO) {
            savePersogensFavoritosUseCase.execute(personagem)
        }
    }

    fun getAllPersonagensFavoritos(): LiveData<List<Personagem>>{
        return getAllPersonagensFavoritosUseCase.execute()
    }

    fun deletePersonagemFavorito(personagem: Personagem){
        viewModelScope.launch(Dispatchers.IO) {
            deletePersonansFavoritosUseCase.execute(personagem)
        }
    }
}