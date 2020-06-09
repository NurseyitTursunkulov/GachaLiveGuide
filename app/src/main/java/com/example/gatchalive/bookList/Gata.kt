package com.example.gatchalive.bookList

import java.util.*

data class Gata(
    val title: String = "dfrfa",
    val id: String = UUID.randomUUID().toString(),
    var imageId: Int,
    var body: String = "",
    var listOfContentPerPage: List<String> = mutableListOf<String>()
)
