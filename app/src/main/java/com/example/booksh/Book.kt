package com.example.booksh

import com.google.firebase.database.Exclude

data class Book(
    @get:Exclude        //l' id non verrà memorizzato nel nodo
    var id: String? = null,
    var title: String? = null,
    var name: String? = null,
    var date: String? = null,
    var genere: String? = null,
    var casaEd: String? = null,
    var voto: Int? = null
)