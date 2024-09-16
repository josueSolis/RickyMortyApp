package com.omni.pro.characters.android.ui.navigation

sealed class Screens(val route: String) {
    data object Home: Screens("Home")
    data object CharacterDetail: Screens("Character/{id}")
}
