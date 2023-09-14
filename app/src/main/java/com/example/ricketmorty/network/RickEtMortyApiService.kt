package com.example.ricketmorty.network

import com.example.ricketmorty.models.CharactersResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.example.ricketmorty.models.Character
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val BASE_URL = "https://rickandmortyapi.com/api/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface RickEtMortyApiService {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character")
    suspend fun getCharactersPage(@Query("page") pageNumber: Int) : CharactersResponse
}

object RickEtMortyApi {
    val retrofitService: RickEtMortyApiService by lazy { retrofit.create(RickEtMortyApiService::class.java) }
}