// File: app/src/main/java/com/chrisp/calmspace/feature/artikel/ArticleScreen.kt

package com.chrisp.calmspace.feature.artikel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chrisp.calmspace.model.ArticleModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    onArticleClick: (ArticleModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ArticleViewModel = viewModel()
) {
    val selectedTab by viewModel.selectedTab
    val searchQuery by viewModel.searchQuery
    val articles = viewModel.filteredArticles
    val tabs = listOf("Semua", "Artikel", "Video")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchBar(
            query = searchQuery,
            onQueryChange = viewModel::setSearchQuery,
            onSearch = { },
            active = false,
            onActiveChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Cari artikel atau video...") },
            leadingIcon = { }
        ) { }

        TabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color(0xFFF5F5F5),
            contentColor = Color(0xFF6750A4),
            indicator = { }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { viewModel.setSelectedTab(index) },
                    modifier = Modifier.padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = if (selectedTab == index) Color(0xFF6750A4) else Color.Gray
                    )
                }
            }
        }

        if (articles.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada artikel yang ditemukan",
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(articles) { article ->
                    ArticleCard(
                        article = article,
                        onArticleClick = onArticleClick
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    article: ArticleModel,
    onArticleClick: (ArticleModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onArticleClick(article) },
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF3EDF7)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = article.image),
                contentDescription = null,
                modifier = Modifier
                    .size(76.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = article.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = article.author,
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                Text(
                    text = article.date,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }
    }
}