package com.example.ricketmorty.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ricketmorty.models.Filter


@Composable
fun FilterDropDown(
    filter: Filter,
    onOptionSelected: (Filter, String?) -> Unit,
) {
    var isDropdownVisible by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(filter.selected) }

    Row(
        modifier = Modifier
            .width(100.dp)
            .padding(0.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selectedOption == null || selectedOption == "") {
            Text(
                text = filter.name,
                fontSize = 14.sp,
            )
            IconButton(
                    onClick = {
                        isDropdownVisible = true
                    },
                ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Open Dropdown",
                    modifier = Modifier.size(18.dp)
                )
            }
        } else {
            Text(
                text = selectedOption.toString(),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
            )
            IconButton(
                onClick = {
                    selectedOption = null
                    onOptionSelected(filter, "")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove selected option",
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        DropdownMenu(
            expanded = isDropdownVisible,
            onDismissRequest = {
                isDropdownVisible = false
            },
            modifier = Modifier
                .width(100.dp)
        ) {
            filter.options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option
                        isDropdownVisible = false
                        onOptionSelected(filter, selectedOption)
                    },
                    text = { Text(text = option) }
                )
            }
        }
    }
}

@Composable
fun FilterBar(modifier: Modifier = Modifier, filterData: List<Filter>, onOptionSelected: (Filter, String?) -> Unit) {
    Row(
        modifier = modifier
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    )
    {filterData.forEach { filter ->
            FilterDropDown(
                filter = filter,
                onOptionSelected = onOptionSelected,
            )
        }
    }
}