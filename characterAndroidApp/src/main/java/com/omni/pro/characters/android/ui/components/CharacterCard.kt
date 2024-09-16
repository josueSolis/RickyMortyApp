package com.omni.pro.characters.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.android.R
import com.omni.pro.characters.domain.EpisodeEntity

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun CharacterCard(character: CharacterEntity) {
    Card(
        Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {

        GlideImage(
            model = character.image,
            contentDescription = character.name,
            loading = placeholder(R.drawable.placeholder_image),
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
        )
        Column(Modifier.padding(8.dp)) {
            TextRow(stringResource(R.string.name_label), character.name)
            TextRow(stringResource(R.string.status_label), character.status.name)
            TextRow(stringResource(R.string.species_label), character.species)
            TextRow(stringResource(R.string.type_label), character.type)
            TextRow(stringResource(R.string.gender_label), character.gender)
            TextRow(stringResource(R.string.origin_label), character.origin.name)
            TextRow(stringResource(R.string.location_label), character.location.name)
            TextRow(
                stringResource(R.string.episodes_label),
                character.episodes.fold(
                    ""
                ) { acc, next -> acc + " " + "\n[${next?.episode}] ${next?.name}" }
            )
        }
    }
}

@Preview
@Composable
private fun CharacterCardPreview() {
    CharacterCard(
        CharacterEntity(
            name = "Morty Smith",
            episodes = listOf(
                EpisodeEntity("", "Close Rick-counters of the Rick Kind", "S01E10"),
                EpisodeEntity("", "Close Rick-counters of the Rick Kind", "S01E10"),
                )
        )
    )
}