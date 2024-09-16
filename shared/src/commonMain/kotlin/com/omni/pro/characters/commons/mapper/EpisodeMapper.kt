package com.omni.pro.characters.commons.mapper

import com.omni.pro.characters.domain.EpisodeEntity
import com.omni.pro.graphql.CharacterQuery

class EpisodeMapper : Mapper<CharacterQuery.Episode, EpisodeEntity> {
    override fun map(input: CharacterQuery.Episode): EpisodeEntity? {
        return EpisodeEntity(
            input.id ?: "",
            input.name ?: "",
            input.episode ?: "",
        )
    }
}