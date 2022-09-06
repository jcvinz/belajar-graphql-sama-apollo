package id.kotlin.rickandmortyproject.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.apollographql.apollo3.ApolloClient
import id.kotlin.rickandmortyproject.MortyCharactersQuery
import javax.inject.Inject

class DataRepository @Inject constructor(private val client: ApolloClient) {
    //    fun getCall() = client.query(MortyCharactersQuery())
    fun getListCharacter(): LiveData<PagingData<MortyCharactersQuery.Result>> {
        return Pager(
            config = PagingConfig(10), pagingSourceFactory = { ListCharacterPagingSource(client) }
        ).liveData
    }
}