package com.example.ricketmorty.models

import com.squareup.moshi.Json

data class CharactersResponse(
    @Json(name = "info") val info: SearchInfo,
    @Json(name = "results") val results: List<Character>
)