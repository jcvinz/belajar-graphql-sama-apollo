package id.kotlin.rickandmortyproject.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import id.kotlin.rickandmortyproject.MortyCharactersQuery
import javax.inject.Inject

class ListCharacterPagingSource @Inject constructor(private val client: ApolloClient) : PagingSource<Int, MortyCharactersQuery.Result>() {

    private val initialPageIndex: Int by lazy { 1 }

    override fun getRefreshKey(state: PagingState<Int, MortyCharactersQuery.Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MortyCharactersQuery.Result> {
        return try {

            val position = params.key ?: initialPageIndex
            val responseData =
                client.query(MortyCharactersQuery(Optional.presentIfNotNull(position))).execute()
            val listCharacter = responseData.data?.characters?.results
            val pageInfo = responseData.data?.characters?.info

            LoadResult.Page(
                data =  listCharacter!!.filterNotNull(),
                prevKey = if (position == initialPageIndex) null else position - 1,
                nextKey = if (pageInfo?.next == null ) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}