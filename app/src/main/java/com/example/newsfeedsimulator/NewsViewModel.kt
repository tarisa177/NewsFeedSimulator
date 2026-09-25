package com.example.newsfeedsimulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList.asStateFlow()

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    init {
        viewModelScope.launch {
            try {

                val newsJob = async {
                    repository.getNewsFlow().collect { news ->
                        _newsList.value = _newsList.value + news
                    }
                }

                newsJob.await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun markAsRead() {
        _readCount.value++
    }
}