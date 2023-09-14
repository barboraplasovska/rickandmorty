package com.example.ricketmorty.models

import com.squareup.moshi.Json

data class SearchInfo(
    @Json(name = "count") val count: Int,
    @Json(name = "pages") val pages: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "prev") val prev: String?
)