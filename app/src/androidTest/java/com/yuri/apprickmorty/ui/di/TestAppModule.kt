package com.yuri.apprickmorty.ui.di

import android.app.Application
import androidx.room.Room
import com.yuri.apprickmorty.BuildConfig
import com.yuri.apprickmorty.data.api.RickMortyApi
import com.yuri.apprickmorty.data.db.PersonagemDao
import com.yuri.apprickmorty.data.db.PersonagemDatabase
import com.yuri.apprickmorty.data.repositories.local.PersonagemLocalDataSource
import com.yuri.apprickmorty.data.repositories.local.PersonagemLocalDataSourceImpl
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSourceImpl
import com.yuri.apprickmorty.domain.repository.PersonagemRepository
import com.yuri.apprickmorty.domain.repository.PersonagemRepositoryImpl
import com.yuri.apprickmorty.domain.usescases.*
import com.yuri.apprickmorty.ui.adapters.ListaPersonagemAdapter
import com.yuri.apprickmorty.ui.adapters.ListaPersonagensFavoritosAdapter
import com.yuri.apprickmorty.ui.viewmodelsfactory.PersonagemViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {
    @Singleton
    @Provides
    fun providePersonagemAdapter(): ListaPersonagemAdapter {
        return ListaPersonagemAdapter()
    }

    @Singleton
    @Provides
    fun providePersonagemFavoritosAdapter(): ListaPersonagensFavoritosAdapter {
        return ListaPersonagensFavoritosAdapter()
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.RICK_MORTY_API_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): RickMortyApi {
        return retrofit.create(RickMortyApi::class.java)
    }

    @Singleton
    @Provides
    fun providePersonagemViewModelFactory(
        getAllPersonagensUseCase: GetAllPersonagensUseCase,
        getPersonagensPorNomeUseCase: GetPersonagensPorNomeUseCase,
        getPersonagensPorStatusEGeneroUseCase: GetPersonagensPorStatusEGeneroUseCase,
        getPersonagensPorGeneroUseCase: GetPersonagensPorGeneroUseCase,
        getPersonagensPorStatusUseCase: GetPersonagensPorStatusUseCase,
        savePersogensFavoritosUseCase: SavePersogensFavoritosUseCase,
        deletePersonansFavoritosUseCase: DeletePersonansFavoritosUseCase,
        getAllPersonagensFavoritosUseCase: GetAllPersonagensFavoritosUseCase
    ): PersonagemViewModelFactory {
        return PersonagemViewModelFactory(
            getAllPersonagensUseCase,
            getPersonagensPorNomeUseCase,
            getPersonagensPorStatusEGeneroUseCase,
            getPersonagensPorGeneroUseCase,
            getPersonagensPorStatusUseCase,
            savePersogensFavoritosUseCase,
            deletePersonansFavoritosUseCase,
            getAllPersonagensFavoritosUseCase
        )
    }

    @Singleton
    @Provides
    fun providePersonagemDataBase(app: Application): PersonagemDatabase {
        return Room.inMemoryDatabaseBuilder(app, PersonagemDatabase::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePersonagemDao(articleDataBase: PersonagemDatabase): PersonagemDao {
        return articleDataBase.getPersonagemDao()
    }

    @Singleton
    @Provides
    fun providePersonagemRepository(
        remoteDataSource: PersonagemRemoteDataSource,
        localDataSource: PersonagemLocalDataSource
    ): PersonagemRepository {
        return PersonagemRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Singleton
    @Provides
    fun providePersonagemLocalDatasource(
        personagemDao: PersonagemDao
    ): PersonagemLocalDataSource {
        return PersonagemLocalDataSourceImpl(personagemDao)
    }

    @Singleton
    @Provides
    fun providePersonagemRemoteDatasource(api: RickMortyApi): PersonagemRemoteDataSource {
        return PersonagemRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun providesGetAllPersonagensUseCase(personagemRepository: PersonagemRepository): GetAllPersonagensUseCase {
        return GetAllPersonagensUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorNomeUseCase(personagemRepository: PersonagemRepository): GetPersonagensPorNomeUseCase {
        return GetPersonagensPorNomeUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorStatusEGenero(personagemRepository: PersonagemRepository): GetPersonagensPorStatusEGeneroUseCase {
        return GetPersonagensPorStatusEGeneroUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorGeneroUseCase(personagemRepository: PersonagemRepository): GetPersonagensPorGeneroUseCase {
        return GetPersonagensPorGeneroUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetPersonagensPorStatusUseCase(personagemRepository: PersonagemRepository): GetPersonagensPorStatusUseCase {
        return GetPersonagensPorStatusUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesSavePersonagensFavoritosUseCase(personagemRepository: PersonagemRepository): SavePersogensFavoritosUseCase {
        return SavePersogensFavoritosUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesDeletePersonagensFavoritosUseCase(personagemRepository: PersonagemRepository): DeletePersonansFavoritosUseCase {
        return DeletePersonansFavoritosUseCase(personagemRepository)
    }

    @Singleton
    @Provides
    fun providesGetAllPersonagensFavoritosUseCase(personagemRepository: PersonagemRepository): GetAllPersonagensFavoritosUseCase {
        return GetAllPersonagensFavoritosUseCase(personagemRepository)
    }
}