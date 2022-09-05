package id.kotlin.rickandmortyproject.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kotlin.rickandmortyproject.data.DataRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataRepo(@Named("apolloClient") apolloClient: ApolloClient) = DataRepository(apolloClient)
}