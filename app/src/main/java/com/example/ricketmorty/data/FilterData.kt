package com.example.ricketmorty.data

import com.example.ricketmorty.models.Filter

val FilterData : List<Filter> = listOf(
    Filter(
        name = "Status",
        options = listOf("Alive", "Dead"),
        selected = null
    ),
    Filter(
        name = "Gender",
        options = listOf("Female", "Male", "Unknown"),
        selected = null
    ),
    Filter(
        name = "Species",
        options = listOf(
            "Human",
            "Alien",
            "Humanoid",
            "Unknown",
            "Poopybutthole",
            "Mythological Creature",
            "Animal",
            "Robot",
            "Cronenberg",
            "Disease"
        ),
        selected = null
    ),
)