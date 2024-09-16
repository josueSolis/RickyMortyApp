package com.omni.pro.characters.repository

import com.omni.pro.characters.commons.ApiException
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.repository.dto.CharacterFilters
import com.omni.pro.characters.repository.dto.CharacterListResponse
import kotlin.coroutines.cancellation.CancellationException

interface CharsApi {
    @Throws(ApiException::class, CancellationException::class)
    suspend fun characters(page: Int, filters: CharacterFilters?): CharacterListResponse?

    @Throws(ApiException::class, CancellationException::class)
    suspend fun character(id: String): CharacterEntity?
}