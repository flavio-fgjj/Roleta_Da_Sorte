package com.example.roleta_da_sorte

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSort = findViewById<Button>(R.id.btn_rodar)
        val numberTyped = findViewById<EditText>(R.id.txt_enunciado)

        fun hideKeyboard() {
            val view = this.currentFocus
            if (view != null) {
                val hideKey = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                hideKey.hideSoftInputFromWindow(view.windowToken, 0)
            } else {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            }
        }

        numberTyped.doAfterTextChanged {
            val i = numberTyped.text.toString().toIntOrNull()
            btnSort.isEnabled = i in 0..10
        }

        btnSort.setOnClickListener {
            val text = findViewById<TextView>(R.id.txt_sorteio)
            val result = findViewById<TextView>(R.id.txt_result)
            val random = Random().nextInt(11)

            val i = text.text.indexOf(":")
            "${text.text.substring(0, i + 1)} $random".also { text.text = it }

            result.text = if (numberTyped.text.toString() == random.toString()) "PARABENS!!!" else "ERRO!!!"
            result.setTextColor(if (result.text.equals("PARABENS!!!")) Color.parseColor("#FF018786") else Color.parseColor("#FF0000"))

            if (result.text.equals("PARABENS!!!")) {
                img_result.isVisible = true
                img_resultDown.isVisible = false
            } else {
                img_result.isVisible = false
                img_resultDown.isVisible = true
            }

            hideKeyboard()
        }
    }
}