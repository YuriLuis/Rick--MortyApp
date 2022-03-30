package com.yuri.apprickmorty.ui.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yuri.apprickmorty.data.models.Personagem
import com.yuri.apprickmorty.data.repositories.remote.PersonagemRemoteDataSource
import com.yuri.apprickmorty.ui.views.PAGINA_INICIAL

class PersonagemPagingSource(
    private val remoteDataSource: PersonagemRemoteDataSource
): PagingSource<Int, Personagem>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Personagem> {
        return try {
            val nextPage: Int = params.key ?: PAGINA_INICIAL
            val response = remoteDataSource.getPersonagens(nextPage)

            var nextPageNumber: Int? = null
            if(response.body()?.info?.next != null) {
                val uri = Uri.parse(response.body()?.info?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            var prevPageNumber: Int? = null
            if(response.body()!!.info.prev != null) {
                val uri = Uri.parse(response.body()!!.info.prev!!)
                val prevPageQuery = uri.getQueryParameter("page")

                prevPageNumber = prevPageQuery?.toInt()
            }

            LoadResult.Page(data = response.body()!!.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber)
        }
        catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Personagem>): Int? {
        return state.anchorPosition
    }
}