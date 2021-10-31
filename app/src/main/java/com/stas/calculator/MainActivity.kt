package com.stas.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private var pairsArr = arrayOf("1INCH-USD","AAVE-USD","ADA-USD","ALGO-USD","ATOM-USD","AVAX-USD","BCH-USD","BTC-USD","COMP-USD","CRV-USD","DOGE-USD","DOT-USD","EOS-USD","ETH-USD","FIL-USD","LINK-USD","LTC-USD","MATIC-USD","MKR-USD","SNX-USD","SOL-USD","SUSHI-USD","UMA-USD","UNI-USD","XMR-USD","YFI-USD","ZEC-USD")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listOfPairs = findViewById<ListView>(R.id.listOfPair)
        val aaChartView = findViewById<AAChartView>(R.id.chart)
        GlobalScope.launch {

        }

        listOfPairs.choiceMode = ListView.CHOICE_MODE_SINGLE
        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_single_choice, pairsArr)
        listOfPairs.adapter = adapter
        listOfPairs.setOnItemClickListener { parent, view, position, id ->
            val selectedPairName = view.findViewById<TextView>(R.id.title)
            val aaChartModel : AAChartModel = AAChartModel()
                .chartType(AAChartType.Column)
                .legendEnabled(false)
                .yAxisTitle("")
                .title(pairsArr[position])
                .backgroundColor("#ffffff")
                .dataLabelsEnabled(true)
                .series(arrayOf(
                    AASeriesElement()
                        .name("Buy")
                        .data(arrayOf((0..20).random(), (0..20).random(), (0..20).random(), (0..20).random(), (0..20).random(), (0..20).random(), (0..20).random())),
                    AASeriesElement()
                        .name("Sell")
                        .data(arrayOf((-20..0).random(), (-20..0).random(), (-20..0).random(), (-20..0).random(), (-20..0).random(), (-20..0).random(), (-20..0).random()))
                )
                )
            aaChartView.aa_drawChartWithChartModel(aaChartModel)

        }
    }


}