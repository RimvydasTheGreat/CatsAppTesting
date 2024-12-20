package com.test.overview.ui.screens.breedsList

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.test.overview.R
import com.test.overview.model.Breed
import com.test.overview.ui.screens.common.BreedDetailsToolbar
import com.test.overview.ui.screens.common.BreedNameText
import com.test.overview.ui.screens.common.CatPhoto

const val IMAGE_URL = "https://cdn2.thecatapi.com/images/"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedListScreen(
    breedsUiState: BreedUiState, onBreedClick: () -> Unit, modifier: Modifier = Modifier
) {
    when (breedsUiState) {
        is BreedUiState.Loading -> LoadingScreen(Modifier.fillMaxSize())
        is BreedUiState.Error -> ErrorScreen(Modifier.fillMaxSize())
        is BreedUiState.Success ->
            Scaffold(containerColor = MaterialTheme.colorScheme.primary,
                modifier = modifier,
                topBar = {
                    BreedDetailsToolbar(
                        screenName = stringResource(id = R.string.breeds_list_screen_title),
                        color = MaterialTheme.colorScheme.primary
                    )
                }) { innerPadding ->
                BreedList(
                    breeds = breedsUiState.breeds,
                    onClick = onBreedClick,
                    modifier = Modifier.padding(innerPadding),
                    cardColor = MaterialTheme.colorScheme.tertiary
                )
            }
    }
}

@Composable
fun BreedList(
    breeds: List<Breed>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardColor: Color = MaterialTheme.colorScheme.background
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(breeds, key = { breed -> breed.id }) { breed ->
            BreedCard(
                breed, onClick, cardColor
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedCard(
    breed: Breed, onClick: () -> Unit, color: Color = MaterialTheme.colorScheme.background
) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.medium_space))
            .testTag("BreedCardTestTag"),
        elevation = CardDefaults
            .cardElevation(
                defaultElevation = dimensionResource(
                    id = R.dimen.medium_space
                )
            ),
        onClick = onClick,
        colors = CardDefaults.cardColors(color)
    ) {
        BreedNameText(
            breed.name,
            24.sp
        )
        CatPhoto(
            imageId = breed.imageId,
            height = dimensionResource(id = R.dimen.cat_photo_on_card_size)
        )
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.loading_img),
        contentDescription = stringResource(id = R.string.loading),
        alignment = Alignment.Center,
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(id = R.string.loading_failed))
    }
}