package com.omni.pro.characters.android.ui.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omni.pro.characters.commons.ApiException
import com.omni.pro.characters.repository.CharsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharacterViewModel constructor(
    private val charsApi: CharsApi,
) : ViewModel() {

    val state: MutableStateFlow<CharacterState> = MutableStateFlow(CharacterState.Default)

    fun loadInfo(id: String) {
        viewModelScope.launch {
            state.emit(CharacterState.IsLoading)
            try {
                val data = charsApi.character(id)
                data?.let {
                    state.emit(CharacterState.Success(it))
                }
            } catch (e: ApiException) {
                state.emit(CharacterState.Failure(e))
            }
        }
    }
}