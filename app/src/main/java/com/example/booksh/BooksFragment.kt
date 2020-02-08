package com.example.booksh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_book.*

class BooksFragment : Fragment() {

    private lateinit var viewModel: BooksViewModel
    private val adapter = BooksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recycler_view_book.adapter = adapter

        viewModel.retrieveBook()

        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.setBooks(it)
        })

        button_add.setOnClickListener{
                    AddBookDialogFragment()
                        .show(childFragmentManager, "")
        }
    }
}