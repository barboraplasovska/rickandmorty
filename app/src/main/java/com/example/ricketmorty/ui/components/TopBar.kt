package com.example.ricketmorty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ricketmorty.R
import com.example.ricketmorty.models.Character
import com.example.ricketmorty.viewmodels.MainViewModel

@Composable
fun TopBar(viewModel: MainViewModel, count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = stringResource(R.string.app_title),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.titleLarge,
                )
                Button(
                    onClick = {
                        viewModel.fetchMoreItems()
                    },
                    shape = RoundedCornerShape(15),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = Color.White,
                    )
                ) {
                    Text(stringResource(R.string.fetch_more))
                }
            }
            Text(stringResource(R.string.character_count, count))
        }
    }
}