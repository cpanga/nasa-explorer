package com.example.nasaexplorer.logic

import com.example.nasaexplorer.logic.NetworkModule.provideNasaApiService
import com.example.nasaexplorer.logic.NetworkModule.provideRetrofit
import com.example.nasaexplorer.ui.screens.astronomyIOTD.AstronomyIOTDRepository
import com.example.nasaexplorer.ui.screens.astronomyIOTD.AstronomyIOTDRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRepository(): AstronomyIOTDRepository {
        return AstronomyIOTDRepositoryImpl(provideNasaApiService(provideRetrofit()))
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.nasa.gov/" // Replace with your base URL

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideNasaApiService(retrofit: Retrofit): NasaApiService {
        return retrofit.create(NasaApiService::class.java)
    }
}