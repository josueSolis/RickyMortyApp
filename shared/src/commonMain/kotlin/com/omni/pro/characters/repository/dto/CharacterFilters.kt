package com.omni.pro.characters.repository.dto

import com.omni.pro.characters.domain.CharacterEnumStatus

data class CharacterFilters(
    val name: String? = null,
    val gender: String? = null,
    val status: CharacterEnumStatus? = null,
    val species: String? = null
)