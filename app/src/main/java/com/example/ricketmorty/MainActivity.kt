package com.example.ricketmorty

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.ricketmorty.navigation.RickEtMortyNav
import com.example.ricketmorty.ui.theme.RickEtMortyTheme
import com.example.ricketmorty.viewmodels.MainViewModel

val viewModel = MainViewModel()

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn( ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickEtMortyTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    RickEtMortyNav(viewModel = viewModel)
                }
            }
        }
    }
}
