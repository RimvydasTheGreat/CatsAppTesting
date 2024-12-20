package com.test.overview.data

import com.test.overview.model.Breed

interface CatsPhotosRepository {
    suspend fun getBreeds(): List<Breed>
}

class NetworkCatsPhotosRepository(private val retrofitService: CatsApiService) :
    CatsPhotosRepository {
    override suspend fun getBreeds(): List<Breed> = retrofitService.getBreeds()
}
