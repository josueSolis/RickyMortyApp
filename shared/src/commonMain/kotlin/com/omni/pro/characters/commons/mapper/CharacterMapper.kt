package com.omni.pro.characters.commons.mapper

import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.domain.CharacterEnumStatus
import com.omni.pro.characters.domain.EpisodeEntity
import com.omni.pro.characters.domain.LocationEntity
import com.omni.pro.graphql.CharacterQuery

class CharacterMapper(
    private val episodeMapper: EpisodeMapper,
) : Mapper<CharacterQuery.Character, CharacterEntity> {

    override fun map(input: CharacterQuery.Character): CharacterEntity? {
        val episodes = ArrayList<EpisodeEntity>()
        input.episode.forEach { episode ->
            episode?.let {
                episodeMapper.map(episode)?.let {
                    episodes.add(it)
                }
            }
        }
        return CharacterEntity(
            id = input.id ?: "",
            name = input.name ?: "",
            status = CharacterEnumStatus.valueOf(input.status ?: "Unknown"),
            species = input.species ?: "",
            gender = input.gender ?: "",
            type = input.type ?: "",
            location = LocationEntity(
                input.location?.id ?: "",
                input.location?.name ?: ""
            ),
            origin = LocationEntity(
                input.origin?.id ?: "",
                input.origin?.name ?: ""
            ),
            image = input.image ?: "",
            episodes = episodes,
            created = input.created ?: ""
        )
    }
}