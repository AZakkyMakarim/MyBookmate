package com.azakkymakarim.mybookmate.model

data class Book(
    val id: Long,
    val title: String,
    val author: String,
    val genre: String,
    val desc: String,
    val cover: String
)