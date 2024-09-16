package com.omni.pro.characters.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Optional
import com.apollographql.apollo.exception.ApolloException
import com.apollographql.apollo.exception.ApolloNetworkException
import com.omni.pro.characters.commons.ApiException
import com.omni.pro.characters.commons.mapper.CharacterMapper
import com.omni.pro.characters.commons.mapper.CharactersMapper
import com.omni.pro.characters.commons.toOptional
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.graphql.CharacterQuery
import com.omni.pro.graphql.CharactersQuery
import com.omni.pro.characters.repository.dto.CharacterFilters
import com.omni.pro.characters.repository.dto.CharacterListResponse
import com.omni.pro.graphql.type.FilterCharacter
import kotlin.Throws

class GraphqlCharsApi(
    private val client: ApolloClient,
    private val charactersMapper: CharactersMapper,
    private val characterMapper: CharacterMapper,
) : CharsApi {

    override suspend fun characters(
        page: Int,
        filters: CharacterFilters?
    ): CharacterListResponse? {
        var filterCharacter: FilterCharacter? = null
        if (filters != null) {
            filterCharacter = FilterCharacter(
                name = filters.name.toOptional(),
                status = filters.status?.name.toOptional(),
                species = filters.species.toOptional(),
                gender = filters.gender.toOptional(),
            )
        }
        val query = CharactersQuery(
            page = Optional.present(page),
            filters = Optional.presentIfNotNull(filterCharacter),
        )
        try {
            val data = client.query(query).execute()
            evalApolloException(data.exception)
            if (data.errors?.isNotEmpty() == true) {
                throw ApiException.ApiError(getApolloErrors(data.errors))
            } else {
                var result: CharacterListResponse? = null
                data.data?.characters?.let { response ->
                    result = charactersMapper.map(response)
                }
                return result
            }
        } catch (e: Exception) {
            throw ApiException.UnknownError(e)
        }
    }

    override suspend fun character(id: String): CharacterEntity? {
        val query = CharacterQuery(id)
        try {
            val data = client.query(query).execute()
           evalApolloException(data.exception)
            if (data.errors?.isNotEmpty() == true) {
                throw ApiException.ApiError(getApolloErrors(data.errors))
            } else {
                var result: CharacterEntity? = null
                data.data?.character?.let { character ->
                    result = characterMapper.map(character)
                }
                return result
            }
        } catch (e: Exception) {
            throw ApiException.UnknownError(e)
        }
    }

    @Throws(Exception::class)
    private fun evalApolloException(exception: ApolloException?) {
        if (exception != null) {
            if (exception is ApolloNetworkException) {
                throw ApiException.NetworkError(exception)
            } else {
                throw ApiException.UnknownError(exception)
            }
        }
    }

    private fun getApolloErrors(errors: List<Error>?): String {
        return errors?.fold("") { acc, next -> acc + " ${next.message}\n" }
            ?: ""
    }
}