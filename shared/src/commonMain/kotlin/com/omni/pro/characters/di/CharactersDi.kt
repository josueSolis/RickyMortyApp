package com.omni.pro.characters.di

import com.omni.pro.characters.repository.CharsApi
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(apolloModule)
    }
}

class CharactersDi : KoinComponent {
    private val charsApi: CharsApi by inject()
    fun getApi(): CharsApi = charsApi
}