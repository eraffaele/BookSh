package com.example.booksh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase

class BooksViewModel : ViewModel() {

    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun addBook(book: Book) {
        val dbBooks = FirebaseDatabase.getInstance().getReference(NODE_AUTHORS)     //creo riferimento al db
        book.id = dbBooks.push().key                    //con push genero la chiave (=id), con key posso recuperarla
        dbBooks.child(book.id!!).setValue(book)         //setto i valori
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }
    }

}