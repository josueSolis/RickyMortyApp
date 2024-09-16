package com.omni.pro.characters.android.di

import com.omni.pro.characters.android.ui.MainViewModel
import com.omni.pro.characters.android.ui.character.CharacterViewModel
import com.omni.pro.characters.android.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<MainViewModel>{ MainViewModel() }
    viewModel<HomeViewModel> { HomeViewModel(get()) }
    viewModel<CharacterViewModel> { CharacterViewModel(get()) }
}