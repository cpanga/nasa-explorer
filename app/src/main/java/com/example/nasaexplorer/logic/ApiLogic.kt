package com.example.nasaexplorer.logic

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {
    @GET(API_URL) // Replace with actual endpoint
    suspend fun getAstronomyImage(
        @Query("api_key") apiKey: String,
    ): AstronomyImageResponse

    companion object {
        const val API_URL = "https://api.nasa.gov/planetary/apod"
    }
}

@JsonClass(generateAdapter = true)
data class AstronomyImageResponse(
    @Json(name = "resource") val resource: Map<String, Any>? = null, // Resource details, may vary in structure
    @Json(name = "concept_tags") val conceptTags: Boolean? = null, // Boolean flag for concept tags
    val title: String, // Title of the image
    val date: String, // Date the image was captured
    val url: String, // URL of the APOD image or video
    val hdurl: String?, // URL for high-resolution image (nullable)
    @Json(name = "media_type") val mediaType: String, // 'image' or 'video'
    val explanation: String, // Explanation of the image
    val concepts: List<String>? = null, // Relevant concepts (only if `concept_tags` is true)
    @Json(name = "thumbnail_url") val thumbnailUrl: String?, // URL for video thumbnail (nullable)
    val copyright: String?, // Copyright holder's name
    @Json(name = "service_version") val serviceVersion: String // Service version
)
