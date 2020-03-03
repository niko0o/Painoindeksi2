package fi.niko0o.painoindeksi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main3.*
import java.lang.reflect.Type

class Main3Activity : AppCompatActivity() {

    val PREFS_FILENAME = "fi.niko0o.painoindeksi2.prefs"
    var lista = arrayListOf<Float>()
    var x = 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        setBarChart()
    }

    private fun setBarChart() {

        val gson = Gson()
        val sharedPrefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        var listaGson = sharedPrefs.getString("lista", "[]")
        val type: Type = object : TypeToken<List<Float?>?>() {}.type
        lista = gson.fromJson(listaGson, type)

        val arvot = ArrayList<BarEntry>()
        for ((index, value) in lista.withIndex()) {

            arvot.add(BarEntry(index.toFloat(), value))
        }
        val barDataSet = BarDataSet(arvot, "1")

        val data = BarData(barDataSet)
        barChart.data = data
        barChart.setScaleEnabled(false)

        barChart.animateY(100)
    }
}

