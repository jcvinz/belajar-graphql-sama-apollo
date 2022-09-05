package id.kotlin.rickandmortyproject.data

import com.apollographql.apollo3.ApolloClient
import id.kotlin.rickandmortyproject.MortyCharactersQuery
import javax.inject.Inject

class DataRepository @Inject constructor (private val client: ApolloClient) {
    fun getCall() = client.query(MortyCharactersQuery())
}