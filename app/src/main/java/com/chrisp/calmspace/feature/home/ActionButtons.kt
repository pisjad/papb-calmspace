package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple80
import com.chrisp.calmspace.ui.theme.White100

@Composable
fun CustomButtons(navController: NavController) {
    // Row to place the buttons side by side
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Konsultasi Button
        ButtonWithIcon(
            icon = Icons.Filled.Info,
            text = "Konsultasi",
            route = Screen.Konsultasi.route,
            navController
        )

        // Forum Button
        ButtonWithIcon(
            icon = Icons.Filled.Info,
            text = "Forum",
            route = Screen.Forum.route,
            navController
        )

        // Artikel Button
        ButtonWithIcon(
            icon = Icons.Filled.ThumbUp,
            text = "Artikel",
            route = Screen.Article.route,
            navController
        )
    }
}

@Composable
fun ButtonWithIcon(icon: ImageVector, text: String, route: String, navController: NavController) {
    Button(
        onClick = {
            navController.navigate(route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        },
        modifier = Modifier
            .size(110.dp) // Size of the button
            .padding(1.dp), // Padding around the button
        shape = RoundedCornerShape(12.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(backgroundColor = Purple80), // Button color
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp) // Optional shadow for the button
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon on top
            Icon(
                imageVector = icon,
                contentDescription = text,
                modifier = Modifier.size(32.dp),
                tint = Color.White // Icon color
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between icon and text
            // Text label below the icon
            Text(
                text = text,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Text color
            )
        }
    }
}


