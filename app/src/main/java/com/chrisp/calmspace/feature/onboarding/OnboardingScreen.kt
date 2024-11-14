package com.chrisp.calmspace.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.Purple60
import com.chrisp.calmspace.ui.theme.White20

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit,
    viewModel: OnboardingViewModel = viewModel()
) {
    val currentIndex by viewModel.currentIndex
    val onboardingItem = viewModel.onboardingItems[currentIndex]

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Purple100)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(23.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = onboardingItem.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = onboardingItem.title,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                color = White20,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = onboardingItem.description,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = White20,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
                        if (currentIndex == viewModel.onboardingItems.size - 1) {
                            onFinish()
                        } else {
                            viewModel.nextScreen()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(200.dp, 50.dp),
                    shape = ShapeDefaults.Medium.copy(CornerSize(9.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple60,
                        contentColor = White20
                    )
                ) {
                    Text(
                        if (currentIndex == viewModel.onboardingItems.size - 1) "Masuk ke CalmSpace" else "Selanjutnya",
                        fontSize = 16.sp,
                    )
                }
                if (currentIndex > 0) {
                    Button(
                        onClick = { viewModel.previousScreen() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .size(200.dp, 50.dp),
                        shape = ShapeDefaults.Medium.copy(CornerSize(9.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple60,
                            contentColor = White20
                        )
                    ) {
                        Text("Kembali", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}