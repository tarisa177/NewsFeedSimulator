package com.example.newsfeedsimulator

import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class NewsRepositoryTest {

    @Test
    fun getNewsFlow_emitsNewsCorrectly() = runTest {
        val repository = NewsRepository()

        val result = repository
            .getNewsFlow()
            .take(3)
            .toList()

        assertEquals(3, result.size)
        assertEquals("Teknologi AI Semakin Berkembang", result[0].title)
        assertEquals("Tips Menjaga Kesehatan", result[1].title)
        assertEquals("Berita Olahraga Hari Ini", result[2].title)
    }

    @Test
    fun getNewsFlow_emitsValidNews() = runTest {
        val repository = NewsRepository()

        val result = repository
            .getNewsFlow()
            .take(5)
            .toList()

        assertTrue(result.isNotEmpty())
        assertTrue(result.all { it.title.isNotEmpty() })
        assertTrue(result.all { it.category.isNotEmpty() })
        assertTrue(result.all { it.description.isNotEmpty() })
    }
}
