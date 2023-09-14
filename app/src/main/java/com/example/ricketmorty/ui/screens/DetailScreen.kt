package com.example.ricketmorty.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import com.example.ricketmorty.models.Character
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.imageLoader
import com.example.ricketmorty.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(character: Character, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                title = {
                    Text(
                        character.name,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = Color.White
                    )
                }
            )
        },
        content = {
            DetailView(character = character)
        }
    )
}


@Composable
fun DetailView(character: Character) {
    Column(modifier = Modifier) {
        AsyncImage(
            model = character.image,
            contentDescription = stringResource(
                R.string.character_image_description,
                character.name
            ),
            imageLoader = LocalContext.current.imageLoader,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        )
        {
            Text(text = stringResource(R.string.character_status, character.status))
            Text(text = stringResource(R.string.character_gender, character.gender))
            Text(text = stringResource(R.string.species, character.species))
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            text = stringResource(R.string.character_origin, character.origin.name)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            text = stringResource(R.string.character_current_location, character.location.name)
        )

    }
}