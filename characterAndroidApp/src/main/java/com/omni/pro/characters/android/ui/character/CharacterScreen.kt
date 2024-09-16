package com.omni.pro.characters.android.ui.character

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omni.pro.characters.android.ui.components.CharacterCard
import com.omni.pro.characters.android.R

@Composable
fun CharacterScreen(
    id: String,
    viewModel: CharacterViewModel = viewModel(),
) {
    val scrollState = rememberScrollState()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadInfo(id)
    }
    Box(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (state) {
            is CharacterState.IsLoading ->  CircularProgressIndicator()
            is CharacterState.Success -> CharacterCard((state as CharacterState.Success).character)
            else -> Text(stringResource(R.string.empty_data))
        }
    }
}

@Preview
@Composable
private fun CharacterScreenPreview() {
    CharacterScreen("1")
}