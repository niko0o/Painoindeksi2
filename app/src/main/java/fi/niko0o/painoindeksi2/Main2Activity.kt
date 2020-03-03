package fi.niko0o.painoindeksi2

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

    val PREFS_FILENAME = "fi.niko0o.painoindeksi2.prefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val sharedPrefs = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

        var pituus = sharedPrefs.getFloat("pituus", 1.80f)

        heightTextField.setText(pituus.toString())

        saveButton.setOnClickListener {
            pituus = heightTextField.text.toString().toFloat()
            val edit = sharedPrefs.edit()
            edit.putFloat("pituus", pituus)
            edit.apply()
            finish()
        }
    }
}
