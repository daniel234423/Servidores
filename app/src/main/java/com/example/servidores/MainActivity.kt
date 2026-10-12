package com.example.servidores

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etPin = findViewById<EditText>(R.id.et_pin)
        val btnVincular = findViewById<Button>(R.id.btn_vincular)
        val btnDemo = findViewById<Button>(R.id.btn_demo)

        val pinesValidos = listOf("123456", "123", "1", "2", "3")

        btnVincular.setOnClickListener {
            val pinIngresado = etPin.text.toString().trim()

            if (pinIngresado.isEmpty()) {
                etPin.error = "Ingresa el PIN de vinculación"
                Toast.makeText(this, "Por favor ingresa un PIN", Toast.LENGTH_SHORT).show()
            } else if (pinesValidos.contains(pinIngresado)) {
                val intent = Intent(this, SalasActivity::class.java)
                startActivity(intent)
            } else {
                // PIN incorrecto
                etPin.error = "PIN incorrecto"
                Toast.makeText(
                    this,
                    "PIN incorrecto. Prueba con: 1, 2, 3 o 123456",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        btnDemo.setOnClickListener {
            val intent = Intent(this, SalasActivity::class.java)
            startActivity(intent)
        }
    }
}