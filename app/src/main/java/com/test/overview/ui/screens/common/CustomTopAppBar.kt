package com.test.overview.ui.screens.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.overview.ui.screens.common.BreedNameText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsToolbar(
    screenName: String? = null,
    onBackClick: (() -> Unit)? = null,
    color: Color = MaterialTheme.colorScheme.background
) {
    val iconModifier = Modifier
        .sizeIn(
            maxWidth = 50.dp,
            maxHeight = 50.dp
        )
        .background(
            color = color,
            shape = CircleShape
        )

    if (onBackClick != null) {
        IconButton(
            onBackClick,
            modifier = Modifier
                .padding(16.dp)
                .then(iconModifier)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
            )
        }
    } else if (screenName != null) {
        TopAppBar(
            title = {
                BreedNameText(
                    screenName,
                    40.sp,
                    MaterialTheme.colorScheme.secondary
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = color)
        )
    }
}
