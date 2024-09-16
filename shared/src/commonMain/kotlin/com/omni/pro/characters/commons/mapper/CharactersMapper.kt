package com.omni.pro.characters.commons.mapper

import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.domain.CharacterEnumStatus
import com.omni.pro.characters.domain.LocationEntity
import com.omni.pro.graphql.CharactersQuery
import com.omni.pro.characters.repository.dto.CharacterListResponse

class CharactersMapper :
    Mapper<CharactersQuery.Characters, CharacterListResponse> {
    override fun map(input: CharactersQuery.Characters): CharacterListResponse {
        val (info, results) = input

        val result = CharacterListResponse(
            info?.count ?: 0,
            info?.pages ?: 0,
            info?.next,
            info?.prev,
            results?.map {
                var locationEntity: LocationEntity? = null
                it?.location?.let { loc ->
                    locationEntity = LocationEntity(
                        id = loc.id ?: "",
                        name = loc.name ?: ""
                    )
                }
                CharacterEntity(
                    id = it?.id ?: "",
                    name = it?.name ?: "",
                    status = CharacterEnumStatus.valueOf(it?.status ?: "unknown"),
                    species = it?.species ?: "",
                    image = it?.image ?: "",
                    location = locationEntity ?: LocationEntity(),
                )
            } ?: emptyList()
        )
        return result
    }
}