package com.example.booksh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.books_details.*
import kotlinx.android.synthetic.main.fragment_book.*

class BooksDetailsFragment(private val book: Book) : DialogFragment(){


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.books_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        textView_auth.text = book.name
        textView_dt.text = book.date
        textView_gen.text = book.genere
        textView_casaEd.text = book.casaEd
        ratingb.rating = book.voto!!.toFloat()

    }


}