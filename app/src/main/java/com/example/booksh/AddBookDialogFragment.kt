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

    private lateinit var viewModel: BooksViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)     //istanza della classe, è diversa poichè estende la classe view Model
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

            val description = edit_text_name_desc.text.toString().trim()
            if (description.isEmpty()) {
                input_layout_name_desc.error = getString(R.string.error_description_required)
                return@setOnClickListener
            }

            val owner = edit_text_name_owner.text.toString().trim()
            if (owner.isEmpty()) {
                input_layout_name_owner.error = getString(R.string.error_owner_required)
                return@setOnClickListener
            }


            val book = Book()
            //'costruisco' l'oggetto libro
            book.name = name
            book.title = title
            book.description = description
            book.owner = owner
            viewModel.addBook(book)
        }
    }
}