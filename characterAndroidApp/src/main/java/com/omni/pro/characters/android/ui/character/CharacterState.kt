package com.omni.pro.characters.android.ui.character

import com.omni.pro.characters.commons.ApiException
import com.omni.pro.characters.domain.CharacterEntity

sealed class CharacterState {
    data object IsLoading : CharacterState()
    data class Failure(val throwable: ApiException) : CharacterState()
    data class Success(val character: CharacterEntity) : CharacterState()
    data object Default : CharacterState()
}