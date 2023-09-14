package com.example.ricketmorty.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ricketmorty.models.Character
import coil.compose.rememberAsyncImagePainter
import com.example.ricketmorty.R

@Composable
fun ListItem(character: Character, onItemClicked: (character: Character) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp, 5.dp, 15.dp, 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = { onItemClicked(character) }),
    ) {
        Row(
            modifier = Modifier
                .background(color = White)
                .fillMaxWidth()
        ) {
            val image: Painter = rememberAsyncImagePainter(character.image)
            Image(
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .clip(RoundedCornerShape(2.dp)),
                painter = image,
                alignment = Alignment.CenterStart,
                contentDescription = stringResource(
                    R.string.character_image_description,
                    character.name
                ),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Text(
                    text = character.name,
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = stringResource(
                        R.string.gender_species,
                        character.species,
                        character.gender
                    ),
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                )

            }
        }
    }
}