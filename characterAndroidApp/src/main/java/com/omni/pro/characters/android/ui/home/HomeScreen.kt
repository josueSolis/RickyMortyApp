package com.omni.pro.characters.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.omni.pro.characters.android.ui.navigation.Screens
import com.omni.pro.characters.android.ui.components.CharacterList

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel<HomeViewModel>(),
    modifier: Modifier = Modifier
) {

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CharacterList(viewModel.pager, modifier) { character ->
            navController.navigate(
                Screens.CharacterDetail.route.replace(
                    "{id}",
                    character.id
                )
            )
        }
    }
}
