package com.test.overview.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.FileDescriptor

@Serializable
data class Breed (
    val id: String,
    val name: String,
    @SerialName("reference_image_id")
    val imageId: String? = null,
    val description: String? = null,
)
