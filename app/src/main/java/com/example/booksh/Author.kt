package com.example.booksh

import com.google.firebase.database.Exclude

data class Author(
    @get:Exclude
    var id: String? = null,
    var name: String? = null
)