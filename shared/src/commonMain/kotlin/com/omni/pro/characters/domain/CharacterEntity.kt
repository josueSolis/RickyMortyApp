package com.omni.pro.characters.domain

data class CharacterEntity(
    val id: String = "",
    val name: String = "",
    val status: CharacterEnumStatus = CharacterEnumStatus.unknown,
    val species: String = "",
    val gender: String = "",
    val type: String = "",
    val location: LocationEntity = LocationEntity(),
    val origin: LocationEntity = LocationEntity(),
    val image: String? = null,
    val episodes: List<EpisodeEntity> = emptyList(),
    val created: String? = null
)