package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import com.chrisp.calmspace.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple60

@Composable
fun CustomButtons(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Konsultasi Button
        ButtonWithIcon(
            iconRes = R.drawable.ic_konsultasi, // Ganti dengan icon yang sesuai
            text = "Konsultasi",
            route = Screen.Konsultasi.route,
            navController = navController
        )

        // Forum Button
        ButtonWithIcon(
            iconRes = R.drawable.ic_forum, // Ganti dengan icon yang sesuai
            text = "Forum",
            route = Screen.Forum.route,
            navController = navController
        )

        // Artikel Button
        ButtonWithIcon(
            iconRes = R.drawable.ic_article, // Ganti dengan icon yang sesuai
            text = "Artikel",
            route = Screen.Article.route,
            navController = navController
        )
    }
}


@Composable
fun ButtonWithIcon(iconRes: Int, text: String, route: String, navController: NavController) {
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
        shape = RoundedCornerShape(8.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(backgroundColor = Purple60), // Button color
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp) // Optional shadow for the button
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Icon from resource
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = text,
                modifier = Modifier.size(36.dp),
                tint = Color.White // Icon color
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between icon and text
            // Text label below the icon
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White // Text color
            )
        }
    }
}


