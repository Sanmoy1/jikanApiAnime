package com.example.jikanapianime.di

import com.example.jikanapianime.data.api.JikanApi
import com.example.jikanapianime.data.repository.AnimeRepositoryImpl
import com.example.jikanapianime.domain.repository.AnimeRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Object responsible for providing network-related dependencies
 */
object NetworkModule {
    /**
     * Creates and returns an instance of JikanApi
     * Configures Retrofit with necessary settings and interceptors
     */
    fun provideJikanApi(): JikanApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(JikanApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JikanApi::class.java)
    }

    /**
     * Provides an instance of AnimeRepository
     * Uses the JikanApi instance for network operations
     */
    fun provideAnimeRepository(): AnimeRepository {
        return AnimeRepositoryImpl(provideJikanApi())
    }
}
