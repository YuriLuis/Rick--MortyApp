package com.yuri.apprickmorty.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class RickMortyApiTest {

    private lateinit var service: RickMortyApi
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RickMortyApi::class.java)
    }

    @Test
    fun getPersonagens() {
        runBlocking {
            enqueueMockResponse("get_personagens.json")
            val response = service.getPersonagens(1).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/api/character?page=1")
        }
    }

    @Test
    fun getPersonagensPorNome() {
        runBlocking {
            enqueueMockResponse("get_personagens_por_nome.json")
            val response = service.getPersonagensPorNome("Rick").body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/api/character?name=Rick")
        }
    }

    @Test
    fun getPersonagensPorStatusEGenero() {
        runBlocking {
            enqueueMockResponse("get_personagens_por_status_genero.json")
            val response = service.getPersonagensPorStatusEGenero("alive", "male", 1).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/api/character?status=alive&gender=male&page=1")
        }
    }

    @Test
    fun getPersonagensPorGenero(){
        runBlocking {
            enqueueMockResponse("get_personagens_por_genero.json")
            val response = service.getPersonagensPorGenero("female", 1).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/api/character?gender=female&page=1")
        }
    }

    @Test
    fun getPersonagensPorStatus(){
        runBlocking {
            enqueueMockResponse("get_personagens_por_status.json")
            val response = service.getPersonagensPorStatus("alive", 1).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/api/character?status=alive&page=1")
        }
    }

    private fun enqueueMockResponse(
        fileName: String
    ) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }
}