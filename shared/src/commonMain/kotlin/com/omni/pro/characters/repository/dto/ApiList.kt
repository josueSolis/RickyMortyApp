package com.omni.pro.characters.repository.dto

import com.omni.pro.characters.domain.CharacterEntity

data class CharacterListResponse(
    val count: Int,
    val pages: Int,
    val next: Int?,
    val prev: Int?,
    val rows: List<CharacterEntity>
)