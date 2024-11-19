package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ArticleCard(
    title: String,
    date: String,
    onClick: () -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .padding(16.dp)
        ) {
            // Image/Icon on the left
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFFFC0CB), shape = MaterialTheme.shapes.small)
            ) {
                Text(
                    text = "Obsessive\nCompulsive\nPersonality\nDisorder",
                    color = Color.Black,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Content on the right
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Text(
                    text = date,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF3A285F),
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                Text(
                    text = "Baca Selengkapnya >",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    ArticleCard(
        title = "Mengenal Obsessive-Compulsive Personality Disorder (OCPD)",
        date = "10 Oktober 2024",
        onClick = { /* Handle click */ }
    )
}
