package fi.niko0o.painoindeksi2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    val PREFS_FILENAME = "fi.niko0o.painoindeksi2.prefs"

    var pituus = 0.0f
    var paino = 0.0f
    var painoindeksi = 0.0f
    var lista = arrayListOf<Float>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val gson = Gson()
        val sharedPrefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        pituus = sharedPrefs.getFloat("pituus", 1.80f)
        var listaGson = sharedPrefs.getString("lista", "[]")
        val type: Type = object : TypeToken<List<Float?>?>() {}.type
        lista = gson.fromJson(listaGson, type)

        textView3.text = "pituus tällä hetkellä: " + pituus
        textView4.text = lista.toString()

        button.setOnClickListener() {
            paino = editText.text.toString().toFloat()
            painoindeksi = paino / (pituus * pituus)
            textView2.text = painoindeksi.toString()
            lista.add(painoindeksi)
            textView4.text = lista.toString()
        }

    }

    override fun onResume() {
        val sharedPrefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        pituus = sharedPrefs.getFloat("pituus", 1.8f)
        textView3.text = "pituus tällä hetkellä: " + pituus
        super.onResume()
    }

    override fun onPause() {
        val sharedPrefs = this.getSharedPreferences(PREFS_FILENAME, 0)
        val gson = Gson()
        val arrayData = gson.toJson(lista)
        val edit = sharedPrefs.edit()
        edit.putString("lista", arrayData)
        edit.apply()
        super.onPause()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this@MainActivity, Main2Activity::class.java)
                startActivity(intent)
                true
            }
            R.id.action_reset -> {
                lista.clear()
                textView4.text = lista.toString()
                true
            }
            R.id.action_palkit -> {
                val intent = Intent(this@MainActivity, Main3Activity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}