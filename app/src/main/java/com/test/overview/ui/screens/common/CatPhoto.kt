package com.test.overview.ui.screens.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.test.overview.R
import com.test.overview.ui.screens.breedsList.IMAGE_URL

@Composable
fun CatPhoto(imageId: String?, height: Dp) {
    val imageUrl = "$IMAGE_URL${imageId}.jpg"
    AsyncImage(
        model = ImageRequest
            .Builder(context = LocalContext.current)
            .data(imageUrl.toUri())
            .crossfade(true)
            .build(),
        contentDescription = stringResource(id = R.string.cat_photo),
        modifier = Modifier
            .height(height)
            .padding(dimensionResource(id = R.dimen.small_space))
            .clip(RoundedCornerShape(10))
            .testTag("CatPhotoTestTag"),
        contentScale = ContentScale.Crop,
        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img)
    )
}