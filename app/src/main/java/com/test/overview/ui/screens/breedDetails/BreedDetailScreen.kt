package com.test.overview.ui.screens.breedDetails

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.overview.R
import com.test.overview.model.Breed
import com.test.overview.ui.screens.common.BreedDetailsToolbar
import com.test.overview.ui.screens.common.BreedNameText
import com.test.overview.ui.screens.common.CatPhoto

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedDetailsScreen(backClick: () -> Unit) {
    Scaffold(
        topBar = {
            BreedDetailsToolbar(
                onBackClick = backClick,
                color = MaterialTheme.colorScheme.tertiary
            )
        }) { BreedDetails() }
}

@Composable
fun BreedDetails() {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        CatPhoto(exampleCatBreed.imageId, 400.dp)
        BreedNameText(
            exampleCatBreed.name,
            36.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Description()
    }
}

@Composable
fun Description() {
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.big_space))
            .fillMaxWidth()
    ) {
        DescriptionText(
            text = exampleCatBreed.description!!,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.small_space))
                .fillMaxSize()
        )
        for (element in temperaments) {
            TemperamentOption(element)
        }
    }
}

@Composable
fun TemperamentOption(temperament: String) {
    Row(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.small_space))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.check_icon),
            contentDescription = null
        )
        DescriptionText(temperament)
    }
}

@Composable
fun DescriptionText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.onPrimary,
    )
}

val exampleCatBreed = Breed(
    "abys",
    "Abyssinian",
    "0XYvRd7oD",
    "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
)
val temperaments = listOf("Active", "Energetic", "Independent", "Intelligent", "Gentle")
