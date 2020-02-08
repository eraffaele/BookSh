package com.example.booksh

import com.google.firebase.database.Exclude

data class Book(
    @get:Exclude
    var id: String? = null,
    var title: String? = null,
    var name: String? = null,
    var date: String? = null
)