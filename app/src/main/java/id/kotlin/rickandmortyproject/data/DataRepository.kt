package id.kotlin.rickandmortyproject.data

import com.apollographql.apollo3.ApolloClient
import id.kotlin.rickandmortyproject.MortyCharactersQuery

class DataRepository (private val client: ApolloClient) {
    fun getCall() = client.query(MortyCharactersQuery())
}