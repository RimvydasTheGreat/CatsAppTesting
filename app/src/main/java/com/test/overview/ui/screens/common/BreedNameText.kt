package com.test.overview.ui.screens.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.test.overview.R

@Composable
fun BreedNameText(
    breedName: String,
    fontSize: TextUnit,
    color: Color = MaterialTheme.colorScheme.onPrimary
) {
    Text(
        text = breedName,
        maxLines = 1,
        modifier = Modifier
            .padding(dimensionResource(R.dimen.medium_space))
            .fillMaxWidth()
            .testTag("BreedNameTestTag"),
        style = TextStyle(
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            fontSize = fontSize,
            color = color
        )
    )
}