package com.example.nasaexplorer.logic

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private interface NasaQueryService {
    @GET("/ws/2/release?type=album&fmt=json")
    fun getAstronomyIOTD(): Call<AstronomyIOTD>

  //  @GET("/ws/2/release/{release}?inc=recordings&fmt=json")
  //  fun getRelease(@Path("release") releaseId: String): Call<Release>

    companion object {
        const val API_URL = "https://api.nasa.gov/planetary/apod"
    }
}

data class Resource(
    val imageSet: String
)

@JsonClass(generateAdapter = true)
data class AstronomyIOTD(
    val resource: Resource,
    @Json(name= "concept_tags") val conceptTags: Boolean,
    val date: String,
    val title: String,
    val url: String,
    val explanation: String,
    val concepts: Map<Int, String>
)

//suspend fun fetchPost(postId: Int): Result<Post> {
//    return withContext(Dispatchers.IO)
//    {
//        try {
//            val response = apiService.getPost(postId)
//            if (response.isSuccessful) {
//                // Handle successful response
//            } else { // Handle HTTP error
//            }
//        } catch (e: Exception) {
//            // Handle network or unexpected exceptions
//        }
//    }

fun getAOTD(): Boolean {
    val retrofit = Retrofit.Builder()
        .baseUrl(NasaQueryService.API_URL)
        .client(getHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val service = retrofit.create(NasaQueryService::class.java)

    val releaseResponse = service.getAstronomyIOTD().execute()
    val release = releaseResponse.body()

    return true
}

private fun getHttpClient(): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    okHttpBuilder.addInterceptor { chain ->
        val requestWithUserAgent = chain.request().newBuilder()
            .header("User-Agent", "My custom user agent")
            .build()
        chain.proceed(requestWithUserAgent)
    }
    return okHttpBuilder.build()
}