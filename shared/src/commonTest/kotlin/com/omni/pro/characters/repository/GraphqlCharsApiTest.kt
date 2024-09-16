package com.omni.pro.characters.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.mockserver.MockServer
import com.apollographql.mockserver.enqueueString
import com.omni.pro.characters.commons.ApiException
import com.omni.pro.characters.commons.mapper.CharacterMapper
import com.omni.pro.characters.commons.mapper.CharactersMapper
import com.omni.pro.characters.commons.mapper.EpisodeMapper
import com.omni.pro.characters.repository.dto.CharacterListResponse
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GraphqlCharsApiTest {
    private lateinit var client: ApolloClient
    private val mockServer = MockServer()
    private lateinit var api: GraphqlCharsApi

    @Test
    fun `characters returns a list of characters when the query is successful`() = runBlocking {
        prepareClient()
        // Given
        val page = 1
        mockServer.enqueueString("""{"data": {"characters": {"info": {"count": 1}, "results": [{"id": "1", "name": "Rick Sanchez"}]}}}""")
        val response = api.characters(page, null)
        assertTrue(response is CharacterListResponse)
    }

    @Test
    fun `character throws a NetworkError when there is a network exception`() = runBlocking {
        prepareClient()
        // Given
        val id = "1"
        mockServer.enqueueString("", 500)
        assertFailsWith<ApiException> {
            api.character(id)
        }
        return@runBlocking
    }

    private suspend fun prepareClient() {
        client = ApolloClient.Builder()
            .serverUrl(mockServer.url())
            .build()
        api = GraphqlCharsApi(client = client, CharactersMapper(), CharacterMapper(EpisodeMapper()))
    }
}