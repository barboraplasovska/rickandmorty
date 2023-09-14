package com.example.ricketmorty.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ricketmorty.models.Character
import com.example.ricketmorty.ui.screens.DetailScreen
import com.example.ricketmorty.ui.screens.ListScreen
import com.example.ricketmorty.viewmodels.MainViewModel

@ExperimentalAnimationApi
@Composable
fun RickEtMortyNav(viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.List.route
    )
    {
        composable(Screen.List.route)
        {
            ListScreen(viewModel = viewModel, navController)
        }
        composable(Screen.Detail.route)
        {
            DetailScreen(character = viewModel.character.value!!, navController)
        }
    }
}