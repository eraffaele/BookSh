package com.example.booksh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BooksViewModel : ViewModel() {

    private val dbBooks = FirebaseDatabase.getInstance().getReference(NODE_AUTHORS)     //creo riferimento al db

    private val _books = MutableLiveData<List<Book>>()
    val books: LiveData<List<Book>>
        get() = _books


    private val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?>
        get() = _result

    fun addBook(book: Book) {
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

    fun retrieveBook(){
        dbBooks.addListenerForSingleValueEvent(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) { //snapshot contiene tutto ciò che sta nel nodo libri
                if(snapshot.exists()){          //controllo se snapshot contiene qualcosa
                    val books = mutableListOf<Book>()

                    for(bookSnapshot in snapshot.children){     //scansiono tutti i figli di snapshot=tutti i sottonodi del nodo libri
                        val book = bookSnapshot.getValue(Book::class.java)      //converto il dato snapshot nella classe Book, saranno riempiti tutti i campi tranne l'id
                        book?.id =  bookSnapshot.key

                        book?.let { books.add(it) }

                    }
                    _books.value = books
                }
            }


        })


    }

}