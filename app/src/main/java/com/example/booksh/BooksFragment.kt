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

class BooksFragment : Fragment(), RecylerViewClickListener {

    private lateinit var viewModel: BooksViewModel      //mi serve per accedere a retrieveBook
    private val adapter = BooksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)     //istanza
        return inflater.inflate(R.layout.fragment_book, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter.listener = this

        recycler_view_book.adapter = adapter        //setto l'adapter

        viewModel.retrieveBook()        //recupero dati
        viewModel.RealTimeUpdates()


        //prendo books di tipo LiveData e lo inserisco nella lista che sarà visualizzata sul display
        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.setBooks(it)
        })


        viewModel.book.observe(viewLifecycleOwner, Observer {
            adapter.addBook(it)
        })


        //cosa accade se clicco sul bottone in basso a dx
        button_add.setOnClickListener{
                    AddBookDialogFragment()
                        .show(childFragmentManager, "")
        }
    }

    override fun onRecyclerViewClickListener(view: View, book: Book) {
        when(view.id){
            R.id.text_view_name -> {
                BooksDetailsFragment(book).show(childFragmentManager, "")
            }
            R.id.button_edit_rv -> {
                EditBookDialogFragment(book).show(childFragmentManager, "")
            }
        }
    }
}