package com.example.booksh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.add_book_dialog_fragment.*

class AddBookDialogFragment : DialogFragment() {
    
    private lateinit var viewModel: BooksViewModel      //mi serve per accedere ad addBook

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)     //istanza
        return inflater.inflate(R.layout.add_book_dialog_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.result.observe(viewLifecycleOwner, Observer {
            val message = if (it == null) {     //il libro è stato aggiunto nel db
                getString(R.string.book_added)
            } else {        //il libro non è stato aggiunto nel db
                getString(R.string.error_db)
            }
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()    //mostra l'errore o il messaggio di successo
            dismiss()
        })

        button_add.setOnClickListener {
            val name = edit_text_name_autore.text.toString().trim()     //metti il nome del libro nella variabile name
            if (name.isEmpty()) {       //se non inserisco il nome dell'autore ho errore
                input_layout_name_autore.error = getString(R.string.error_author_required)
                return@setOnClickListener
            }

            val title = edit_text_name_titolo.text.toString().trim()
            if (title.isEmpty()) {
                input_layout_name_titolo.error = getString(R.string.error_title_required)
                return@setOnClickListener
            }

            val data = edit_text_name_data.text.toString().trim()
            if (data.isEmpty()) {
                input_layout_name_data.error = getString(R.string.error_data_required)
                return@setOnClickListener
            }

            val genere = edit_text_name_genere.text.toString().trim()
            if (genere.isEmpty()) {
                input_layout_name_genere.error = getString(R.string.error_genere_required)
                return@setOnClickListener
            }

            val casaEd = edit_text_name_casaEd.text.toString().trim()
            if (casaEd.isEmpty()) {
                input_layout_name_casaEd.error = getString(R.string.error_casaEd_required)
                return@setOnClickListener
            }

            val numStar = ratingbar.rating.toInt()
            if (numStar == 0) {
                input_layout_name_rating.error = getString(R.string.error_star_required)
                return@setOnClickListener
            }

            val book = Book()
            book.title = title
            book.name = name
            book.date = data
            book.genere = genere
            book.casaEd = casaEd
            book.voto = numStar
            viewModel.addBook(book)
        }
    }
}