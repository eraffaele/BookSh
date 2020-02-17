package com.example.booksh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_view_book.view.*

class BooksAdapter : RecyclerView.Adapter<BooksAdapter.BookViewModel>() {       //mi serve per visualizzare la recycler view

    private var books = mutableListOf<Book>()
    var listener: RecylerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BookViewModel(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_book, parent, false)
    )

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: BookViewModel, position: Int) {
        holder.view.text_view_name.text = books[position].title
        holder.view.text_view_name.setOnClickListener {
            listener?.onRecyclerViewClickListener(it, books[position])
        }
        holder.view.button_edit_rv.setOnClickListener {
            listener?.onRecyclerViewClickListener(it, books[position])
        }
    }

    fun setBooks(books: List<Book>){        //setto la lista
        this.books = books as MutableList<Book>
        notifyDataSetChanged()      //visualizzo sul display
    }

    //utile per aggiornamenti realtime
   // fun addBook(book: Book){
     //   if(!books.contains(book)) {
       //     books.add(book)
        //} else {
          // val index = books.indexOf(book)
            //books[index] = book
        //}
        //notifyDataSetChanged()
    //}

    class BookViewModel(val view: View) : RecyclerView.ViewHolder(view)
}

