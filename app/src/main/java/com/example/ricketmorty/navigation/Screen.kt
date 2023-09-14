package com.example.ricketmorty.navigation

sealed class Screen (val route: String)
{
    object List: Screen("ListScreen")
    object Detail: Screen("DetailScreen")
}