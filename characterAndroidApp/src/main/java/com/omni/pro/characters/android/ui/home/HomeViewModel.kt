package com.omni.pro.characters.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.omni.pro.characters.android.repository.CharactersDataSource
import com.omni.pro.characters.repository.CharsApi

class HomeViewModel (
    private val charsApi: CharsApi,
) : ViewModel() {
    val pager = Pager(PagingConfig(20)) {
        CharactersDataSource(charsApi, null)
    }
}