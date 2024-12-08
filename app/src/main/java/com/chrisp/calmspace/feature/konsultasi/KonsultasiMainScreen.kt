package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen

@Composable
fun KonsultasiScreen(navController: NavController) {
    val viewModel: KonsultasiViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    Scaffold(
        bottomBar = {BottomNavigationBar(navController)}
    ) {
        paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues).fillMaxSize()) {
            // Search Bar
            SearchBar(state.searchQuery, onSearch = { query ->
                viewModel.updateSearchQuery(query)
            })

            // Tab Row
            TabRow(selectedTabIndex = state.selectedTab) {
                state.tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = state.selectedTab == index,
                        onClick = { viewModel.updateSelectedTab(index) }
                    ) {
                        Text(
                            text = title,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            // Content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when (state.selectedTab) {
                    0 -> Text(
                        "Aktif Content",
                        Modifier.align(Alignment.Center),
                        fontSize = 14.sp, // Ukuran font konten
                        fontWeight = FontWeight.Bold // Tulisan tebal
                    )
                    1 -> Text(
                        "Terjadwal Content",
                        Modifier.align(Alignment.Center),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    2 -> Text(
                        "Riwayat Content",
                        Modifier.align(Alignment.Center),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            androidx.compose.material3.FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.JadwalKonsultasi.route) {
                        popUpTo(Screen.Konsultasi.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(16.dp)// Memposisikan di kanan bawah
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Post"
                )
            }
            }


    }
}

@Composable
fun SearchBar(query: String, onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue(query)) }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray, MaterialTheme.shapes.small)
            .padding(8.dp)
    )
}


