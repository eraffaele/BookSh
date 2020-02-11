package com.example.booksh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import kotlinx.android.synthetic.main.pie_chart.*

class PieChartFragment : DialogFragment() {


    private val months = arrayOf("Jan", "Feb", "Mar")
    private val earnings = intArrayOf(500, 1000, 200)


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

        val  pie = AnyChart.pie3d()        //inizializzo
        val dataEntries = arrayListOf<DataEntry>()
        for(i in 0..months.size - 1){
            dataEntries.add(ValueDataEntry(months[i], earnings[i]))
        }

        pie.data(dataEntries)       //passo i dati al grafico
        pie.title("Earnings")
        any_chart_view.setChart(pie) //non ho bisogno di findViewId
    }


}