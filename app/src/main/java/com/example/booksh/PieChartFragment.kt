package com.example.booksh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.pie_chart.*

class PieChartFragment : DialogFragment() {

    private val dbBooks = FirebaseDatabase.getInstance().getReference("libri")     //creo riferimento al db

    private val months = arrayOf("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio",
                                    "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre")
    var data = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pie_chart, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupChart()

    }

    private fun setupChart(){
        dbBooks.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(bookSnapshot in snapshot.children){
                        val book = bookSnapshot.getValue(Book::class.java)
                        book?.id =  bookSnapshot.key

                        when (book?.date){
                            "Gennaio" -> {data[0] = data[0]+1}
                            "Febbraio" -> {data[1] = data[1]+1}
                            "Marzo" -> {data[2] = data[2]+1}
                            "Aprile" -> {data[3] = data[3]+1}
                            "Maggio" -> {data[4] = data[4]+1}
                            "Giugno" ->{data[5] = data[5]+1}
                            "Luglio" -> {data[6] = data[6]+1}
                            "Agosto" -> {data[7] = data[7]+1 }
                            "Settembre" -> {data[8] = data[8]+1}
                            "Ottobre" -> {data[9] = data[9]+1}
                            "Novembre" -> {data[10] = data[10]+1}
                            else  -> {data[11] = data[11]+1}
                        }

                    }

                }

                val  pie = AnyChart.pie3d()        //inizializzo
                val dataEntries = arrayListOf<DataEntry>()
                for(i in 0..months.size - 1){
                    dataEntries.add(ValueDataEntry(months[i], data[i]))
                }

                pie.data(dataEntries)       //passo i dati al grafico
                pie.title("Statistica dei mesi in cui hai letto di più")
                any_chart_view.setChart(pie)

            }


        })
    }


}