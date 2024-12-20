package com.test.overview.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.test.overview.model.Breed
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val API_KEY = "live_mnKUzilyj43PXxwB9MHr4Uzxz1C75w4xYfhFo7zkpb3jG3i2nchzmn4V4qZfARCB"

interface CatsApiService {
    @GET("v1/breeds")
    suspend fun getBreeds(): List<Breed>
}



class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.thecatapi.com"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(
            json
                .asConverterFactory("application/json".toMediaType())
        )
        .baseUrl(baseUrl)
        .build()

    private val retrofitCatsPhotoService: CatsApiService by lazy {
        retrofit.create(CatsApiService::class.java)
    }

    override val catsPhotoRepository: CatsPhotosRepository =
        NetworkCatsPhotosRepository(retrofitCatsPhotoService)
}