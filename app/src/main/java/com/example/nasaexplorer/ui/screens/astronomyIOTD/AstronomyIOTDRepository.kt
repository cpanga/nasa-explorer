package com.example.nasaexplorer.ui.screens.astronomyIOTD

import com.example.nasaexplorer.logic.AstronomyImageResponse
import com.example.nasaexplorer.logic.NasaApiService
import javax.inject.Inject

interface AstronomyIOTDRepository {
    suspend fun getAstronomyImage(apiKey: String): AstronomyImageResponse
}

class AstronomyIOTDRepositoryImpl @Inject constructor(
    private val apiService: NasaApiService
) : AstronomyIOTDRepository {
    override suspend fun getAstronomyImage(apiKey: String): AstronomyImageResponse {
        return apiService.getAstronomyImage(apiKey) // Fetch data from API
    }
}