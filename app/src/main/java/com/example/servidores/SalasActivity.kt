package com.example.servidores

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView

class SalasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salas)

        val cardSala1 = findViewById<MaterialCardView>(R.id.card_sala_1)
        val cardSala2 = findViewById<MaterialCardView>(R.id.card_sala_2)
        val cardSala3 = findViewById<MaterialCardView>(R.id.card_sala_3)

        cardSala1.setOnClickListener {
            abrirDetalleSala(
                titulo = "Sala 1",
                servidor = "SRV-A",
                tempActual = "24.8°C",
                badgeTexto = "OK",
                badgeTipo = "ok",
                estadoRango = "OK (18 a 26 °C)",
                ultimaLectura = "14:32:00",
                min = "21.2°C",
                prom = "23.5°C",
                max = "25.1°C",
                chartData = floatArrayOf(0.15f, 0.22f, 0.30f, 0.28f, 0.35f, 0.33f)
            )
        }

        cardSala2.setOnClickListener {
            abrirDetalleSala(
                titulo = "Sala 2",
                servidor = "SRV-B",
                tempActual = "29.6°C",
                badgeTexto = "ADVERTENCIA",
                badgeTipo = "warning",
                estadoRango = "Advertencia (27 a 32 °C)",
                ultimaLectura = "14:32:05",
                min = "23.9°C",
                prom = "27.1°C",
                max = "29.9°C",
                chartData = floatArrayOf(0.2f, 0.35f, 0.6f, 0.55f, 0.7f, 0.68f)
            )
        }

        cardSala3.setOnClickListener {
            abrirDetalleSala(
                titulo = "Sala 3",
                servidor = "SRV-C",
                tempActual = "34.2°C",
                badgeTexto = "CRÍTICO",
                badgeTipo = "critical",
                estadoRango = "Crítico (> 32 °C)",
                ultimaLectura = "14:31:50",
                min = "26.1°C",
                prom = "30.8°C",
                max = "35.0°C",
                chartData = floatArrayOf(0.3f, 0.45f, 0.7f, 0.8f, 0.9f, 0.95f)
            )
        }
    }

    private fun abrirDetalleSala(
        titulo: String,
        servidor: String,
        tempActual: String,
        badgeTexto: String,
        badgeTipo: String,
        estadoRango: String,
        ultimaLectura: String,
        min: String,
        prom: String,
        max: String,
        chartData: FloatArray
    ) {
        val intent = Intent(this, DetalleSalaActivity::class.java).apply {
            putExtra("EXTRA_TITULO", titulo)
            putExtra("EXTRA_SERVIDOR", servidor)
            putExtra("EXTRA_TEMP_ACTUAL", tempActual)
            putExtra("EXTRA_BADGE_TEXTO", badgeTexto)
            putExtra("EXTRA_BADGE_TIPO", badgeTipo)
            putExtra("EXTRA_ESTADO_RANGO", estadoRango)
            putExtra("EXTRA_ULTIMA_LECTURA", ultimaLectura)
            putExtra("EXTRA_MIN", min)
            putExtra("EXTRA_PROM", prom)
            putExtra("EXTRA_MAX", max)
            putExtra("EXTRA_CHART_DATA", chartData)
        }
        startActivity(intent)
    }
}