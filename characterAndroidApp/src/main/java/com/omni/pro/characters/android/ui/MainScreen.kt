package com.omni.pro.characters.android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.omni.pro.characters.android.R
import com.omni.pro.characters.android.ui.character.CharacterScreen
import com.omni.pro.characters.android.ui.character.CharacterViewModel
import com.omni.pro.characters.android.ui.home.HomeScreen
import com.omni.pro.characters.android.ui.home.HomeViewModel
import com.omni.pro.characters.android.ui.navigation.Screens
import com.omni.pro.characters.android.ui.theme.CharactersAppTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val messages by viewModel.messages.collectAsState()
    val scope = rememberCoroutineScope()
    messages.dropLast(1).takeIf { it.isNotEmpty() }?.let {
        scope.launch {
            snackbarHostState.showSnackbar(it, duration = SnackbarDuration.Short)
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                { Text(stringResource(R.string.app_name)) },
                navigationIcon = {
                    when (navBackStackEntry?.destination?.route) {
                        Screens.Home.route -> Image(
                            imageVector = Icons.Filled.Home,
                            "",
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )

                        else -> Image(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            "",
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            },
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )
                    }
                }
            )
        }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screens.Home.route,
        ) {
            composable(Screens.Home.route) {
                val viewModel = koinViewModel<HomeViewModel>()
                HomeScreen(navController, viewModel)
            }
            composable(Screens.CharacterDetail.route) { backStackEntry ->
                val viewModel = koinViewModel<CharacterViewModel>()
                CharacterScreen(backStackEntry.arguments?.getString("id") ?: "", viewModel)
            }
        }

    }
}

@Preview
@Composable
fun MainScreenPreview() {
    CharactersAppTheme(dynamicColor = false) {
        MainScreen()
    }
}