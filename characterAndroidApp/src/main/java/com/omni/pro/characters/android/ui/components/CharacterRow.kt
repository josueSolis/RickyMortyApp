package com.omni.pro.characters.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.omni.pro.characters.domain.CharacterEntity
import com.omni.pro.characters.domain.LocationEntity
import com.omni.pro.characters.android.ui.theme.Typography
import com.omni.pro.characters.android.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterRow(
    character: CharacterEntity,
    modifier: Modifier = Modifier,
    onClick: (CharacterEntity) -> Unit,
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.Red)
            .clickable { onClick(character) },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary,
            headlineColor = MaterialTheme.colorScheme.onPrimary
        ),
        leadingContent = {
            GlideImage(
                character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .clip(shape = CircleShape),
                loading = placeholder(R.drawable.placeholder_image)
            )
        },
        headlineContent = {
            Column {
                Text(character.name, style = Typography.bodyLarge)
                Spacer(Modifier.size(4.dp))
                Text(character.location.name, style = Typography.labelSmall)
            }
        }
    )
}

@Preview
@Composable
fun CharacterRowPreview() {
    CharacterRow(
        CharacterEntity(
            name = "Summer Smith",
            location = LocationEntity(
                "20",
                "Earth (Replacement Dimension)"
            )
        )
    ) {}
}