package com.yuri.apprickmorty.ui.main.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yuri.apprickmorty.data.repositories.FakePersonagemRepository
import com.yuri.apprickmorty.utils.MainCoroutineScopeRule
import com.yuri.apprickmorty.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PersonagemViewModelTest {
    private lateinit var viewModel: PersonagemViewModel
    private lateinit var repository: FakePersonagemRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = FakePersonagemRepository()
        viewModel = PersonagemViewModel(repository)

    }

    @Test
    fun `view model personagem deve retorna lista com todos personagens`() {
        viewModel.getPersonagens(1)
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result.size).isEqualTo(12)
        assertThat(result[0].nome).isEqualTo("nome01")
    }

    @Test
    fun `view model deve retorna lista de personagens com nome pesquisado`() {
        viewModel.getPersonagensPorNome("01")
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result).isNotEmpty()
        assertThat(result.size).isEqualTo(4)
    }

    @Test
    fun `view model deve retornar lista de personagens de acordo com status e genero`() {
        viewModel.getPersonagensPorStatusEGenero(
            "alive", "male", 1
        )
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result).isNotEmpty()
        assertThat(result.size).isEqualTo(4)
    }

    @Test
    fun `view model deve retornar lista de personagens de acordo com genero`() {
        viewModel.getPersonagensPorGenero(
            "male", 1
        )
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result).isNotEmpty()
        assertThat(result.size).isEqualTo(7)
    }

    @Test
    fun `view model deve retornar lista de personagens de acordo com status`() {
        viewModel.getPersonagensPorStatus("dead", 1)
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result).isNotEmpty()
        assertThat(result.size).isEqualTo(5)
    }

    @Test
    fun `view model deve retorna null se houver erro com internet`(){
        repository.houveErroInternet = true
        viewModel.getPersonagens(1)
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result).isNull()
    }
}