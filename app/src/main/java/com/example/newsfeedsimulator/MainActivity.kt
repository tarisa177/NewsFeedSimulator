package com.example.newsfeedsimulator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsFeedScreen()
        }
    }
}

@Composable
fun NewsFeedScreen() {
    val viewModel = remember { NewsViewModel() }

    val newsList by viewModel.newsList.collectAsState()
    val readCount by viewModel.readCount.collectAsState()

    var selectedCategory by remember {
        mutableStateOf("Semua")
    }

    val categories = listOf(
        "Semua",
        "Teknologi",
        "Kesehatan",
        "Olahraga",
        "Ekonomi",
        "Pendidikan"
    )

    val filteredNews = if (selectedCategory == "Semua") {
        newsList
    } else {
        newsList.filter {
            it.category == selectedCategory
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "News Feed Simulator",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Berita dibaca: $readCount",
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 12.dp
            )
        )

        Text(
            text = "Filter Kategori",
            style = MaterialTheme.typography.titleMedium
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {

            categories.chunked(3).forEach { rowCategories ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    rowCategories.forEach { category ->

                        Button(
                            onClick = {
                                selectedCategory = category
                            }
                        ) {
                            Text(category)
                        }
                    }
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = filteredNews,
                key = { it.id }
            ) { news ->

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = news.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = "Kategori: ${news.category}",
                            modifier = Modifier.padding(top = 4.dp)
                        )

                        Text(
                            text = news.description,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                        Button(
                            onClick = {
                                viewModel.markAsRead()
                            },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text("Tandai Dibaca")
                        }
                    }
                }
            }
        }
    }
}