package com.example.booksh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        nav_view.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_stat -> {
                    PieChartFragment().show(fragment.childFragmentManager, "")
                        true
                }
                R.id.navigation_add -> {
                    AddBookDialogFragment().show(fragment.childFragmentManager, "")
                    true
                }
                R.id.navigation_home -> {
                    BooksFragment()
                    true
                }
                else -> false
            }
        }

    }
}
