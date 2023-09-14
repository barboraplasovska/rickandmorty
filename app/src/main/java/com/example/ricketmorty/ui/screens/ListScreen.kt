package com.example.ricketmorty.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavController
import com.example.ricketmorty.data.FilterData
import com.example.ricketmorty.models.Character
import com.example.ricketmorty.navigation.Screen
import com.example.ricketmorty.ui.components.FilterBar
import com.example.ricketmorty.ui.components.ListItem
import com.example.ricketmorty.ui.components.TextSearchBar
import com.example.ricketmorty.ui.components.TopBar
import com.example.ricketmorty.viewmodels.MainViewModel

@Composable
fun LazyListState.OnBottomReached(
    loadMore: () -> Unit
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect {
                if (it) loadMore()
            }
    }
}

@Composable
fun ListScreen(viewModel: MainViewModel, navController: NavController) {
    val characters: List<Character> by viewModel.characters.observeAsState(initial = listOf())
    val filterData by viewModel.filter_data.observeAsState(initial = listOf())

    val listState = rememberLazyListState()

    var searchedText by remember { mutableStateOf("") }

    val view = LocalView.current

    val filteredCharacters = characters.filter { character ->
        Log.v("FILTER", "Filtering characters")
        val selectedStatus = filterData.find { it.name == "Status" }?.selected ?: ""
        val selectedGender = filterData.find { it.name == "Gender" }?.selected ?: ""
        val selectedSpecies = filterData.find { it.name == "Species" }?.selected ?: ""

        val statusMatch =
            selectedStatus.isBlank() || character.status.equals(selectedStatus, ignoreCase = true)
        val genderMatch =
            selectedGender.isBlank() || character.gender.equals(selectedGender, ignoreCase = true)
        val speciesMatch = selectedSpecies.isBlank() || character.species.equals(
            selectedSpecies,
            ignoreCase = true
        )

        character.name.contains(searchedText, ignoreCase = true) &&
                statusMatch &&
                genderMatch &&
                speciesMatch
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopBar(viewModel = viewModel, count = filteredCharacters.size)
        }
        item {
            TextSearchBar(
                modifier = Modifier,
                value = searchedText,
                onDoneActionClick = {
                    view.clearFocus()
                    searchedText = ""
                },
                onClearClick = {
                    searchedText = ""
                    view.clearFocus()
                },
                onValueChanged = { query ->
                    searchedText = query
                },
            )
        }
        item {
            FilterBar(
                filterData = filterData,
                onOptionSelected = { filter, s ->
                    viewModel.updateFilterData(filter = filter, selected = s)

                }
            )
        }
        items(count = filteredCharacters.size) {
            ListItem(filteredCharacters[it],
                onItemClicked = { character ->
                    navController.navigate(Screen.Detail.route)
                    viewModel.selectCharacter(character)
                })
        }
    }

    listState.OnBottomReached {
        viewModel.fetchMoreItems()
    }
}