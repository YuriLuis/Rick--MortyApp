package com.yuri.apprickmorty.ui.main.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.yuri.apprickmorty.data.models.LocacaoData
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.models.PersonagemResponse
import com.yuri.apprickmorty.data.repositories.FakePersonagemRepository
import com.yuri.apprickmorty.data.repositories.PersonagemRepository
import com.yuri.apprickmorty.data.repositories.PersonagemRepositoryImpl
import com.yuri.apprickmorty.utils.MainCoroutineScopeRule
import com.yuri.apprickmorty.utils.Resource
import com.yuri.apprickmorty.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

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
        //repository = PersonagemRepositoryImpl(RickMortyRetrofit.apiRickMorty)
        viewModel = PersonagemViewModel(repository)

    }

    @Test
    fun `view model personagem deve retorna lista de personagem`() = runBlockingTest {
        viewModel.getPersonagens(1)
        val result = viewModel.personagensLiveData.getOrAwaitValue()
        assertThat(result.size).isEqualTo(3)
        assertThat(result[0].nome).isEqualTo("nome01")
    }
}


