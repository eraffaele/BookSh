package com.example.booksh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*

class BooksViewModel : ViewModel() {

    private val dbBooks = FirebaseDatabase.getInstance().getReference("libri")     //creo riferimento al db

    private val _books = MutableLiveData<List<Book>>()      //lista in cui vanno i titoli dei libri
    val books: LiveData<List<Book>>
        get() = _books


    private val _result = MutableLiveData<Exception?>()     //mi serve per capire se il libro è stato salvato o meno
    val result: LiveData<Exception?>                        //la uso per accedere a _result fuori da BooksViewModel
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


    fun retrieveBook(){     //mi restituisce il titolo del libro
        dbBooks.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) { //snapshot contiene tutto ciò che sta nel nodo libri
                if(snapshot.exists()){          //controllo se snapshot contiene qualcosa
                    val books = mutableListOf<Book>()           //ci vanno i titoli dei libri

                    for(bookSnapshot in snapshot.children){     //scansiono tutti i figli di snapshot=tutti i sottonodi del nodo libri
                        val book = bookSnapshot.getValue(Book::class.java)      //converto il dato snapshot nella classe Book, saranno riempiti tutti i campi tranne l'id
                        book?.id =  bookSnapshot.key                //aggiunta campo id

                        book?.let { books.add(it) }                 //se book non è nullo lo aggiunto alla lista

                    }
                    _books.value = books


                }
            }


        })

    }

    fun updateBook(book: Book){
        dbBooks.child(book.id!!).setValue(book)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    _result.value = null
                } else {
                    _result.value = it.exception
                }
            }

    }


}