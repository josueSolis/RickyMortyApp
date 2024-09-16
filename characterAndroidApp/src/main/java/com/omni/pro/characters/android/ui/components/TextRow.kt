package com.omni.pro.characters.android.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextRow(
    label: String?,
    value: String?
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            label ?: "",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Spacer(Modifier.size(16.dp))
        Text(
            value ?: "", style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}

@Preview
@Composable
private fun TextRowPreview() {
    TextRow("Nombre", "Morty Smith")
}