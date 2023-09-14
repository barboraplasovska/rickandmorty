package com.example.ricketmorty.models

data class Filter(
    val selected: String?,
    val name: String,
    val options: List<String>,
)