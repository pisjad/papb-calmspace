package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
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

@Composable
fun KonsultasiScreen(viewModel: KonsultasiViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
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

        // Floating Action Button
        FloatingActionButton(
            onClick = { /* TODO: Add action */ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Text("+", color = Color.White)
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

@Composable
@Preview(showBackground = true)
fun prev() {
    KonsultasiScreen()
}
