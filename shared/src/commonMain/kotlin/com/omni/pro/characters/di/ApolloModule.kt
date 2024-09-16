package com.omni.pro.characters.di

import com.apollographql.apollo.ApolloClient
import com.omni.pro.characters.commons.mapper.CharacterMapper
import com.omni.pro.characters.commons.mapper.CharactersMapper
import com.omni.pro.characters.commons.mapper.EpisodeMapper
import com.omni.pro.characters.repository.CharsApi
import com.omni.pro.characters.repository.GraphqlCharsApi
import org.koin.dsl.module

val apolloModule = module {
    single<ApolloClient> {
        ApolloClient.Builder().serverUrl("https://rickandmortyapi.com/graphql").build()
    }

    single<CharacterMapper> { CharacterMapper(EpisodeMapper()) }

    single<CharactersMapper> { CharactersMapper() }

    single<CharsApi> {
        GraphqlCharsApi(client = get(), get(), get())
    }

}