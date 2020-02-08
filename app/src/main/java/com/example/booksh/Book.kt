package com.example.booksh

import com.google.firebase.database.Exclude

data class Book(
    @get:Exclude        //l' id non verrà memorizzato nel nodo
    var id: String? = null,
    var title: String? = null,
    var name: String? = null,
    var date: String? = null
){
    //mi serve per evitare titoli duplicati
    override fun equals(other: Any?): Boolean {
        return if(other is Book){   //other è un'istanza di Book
            other.id == id          //il libro è già presente
        }   else false
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        return result
    }


}