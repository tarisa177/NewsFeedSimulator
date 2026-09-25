package com.example.newsfeedsimulator

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class NewsRepository {

    private val newsList = listOf(
        News(
            1,
            "Teknologi AI Semakin Berkembang",
            "Teknologi",
            "Perkembangan kecerdasan buatan semakin pesat."
        ),
        News(
            2,
            "Tips Menjaga Kesehatan",
            "Kesehatan",
            "Menjaga pola hidup sehat penting untuk tubuh."
        ),
        News(
            3,
            "Berita Olahraga Hari Ini",
            "Olahraga",
            "Informasi terbaru dari dunia olahraga."
        ),
        News(
            4,
            "Perkembangan Ekonomi Indonesia",
            "Ekonomi",
            "Kondisi ekonomi terus mengalami perubahan."
        ),
        News(
            5,
            "Tips Belajar Efektif",
            "Pendidikan",
            "Belajar secara teratur dapat membantu meningkatkan pemahaman."
        ),
        News(
            6,
            "Smartphone Terbaru Diluncurkan",
            "Teknologi",
            "Perkembangan smartphone semakin cepat dengan fitur baru."
        ),
        News(
            7,
            "Pentingnya Olahraga Setiap Hari",
            "Olahraga",
            "Aktivitas fisik secara rutin membantu menjaga kebugaran."
        ),
        News(
            8,
            "Metode Belajar Modern",
            "Pendidikan",
            "Teknologi membantu proses belajar menjadi lebih interaktif."
        ),
        News(
            9,
            "Perubahan Ekonomi Masyarakat",
            "Ekonomi",
            "Kondisi ekonomi terus mengalami perubahan."
        ),
        News(
            10,
            "Pola Hidup Sehat",
            "Kesehatan",
            "Menjaga pola hidup sehat dapat membantu tubuh tetap bugar."
        )
    )

    fun getNewsFlow(): Flow<News> = flow {
        while (true) {
            for (news in newsList) {
                emit(news)
                delay(2000)
            }
        }
    }
    .filter { it.title.isNotEmpty() }
    .map { it }
    .onEach { println("Berita baru: ${it.title}") }
}