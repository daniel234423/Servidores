package com.example.servidores

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

class DetalleSalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_sala)

        val btnVolver = findViewById<MaterialCardView>(R.id.btn_volver)
        val tvTituloSala = findViewById<TextView>(R.id.tv_titulo_sala)
        val tvTemperaturaActual = findViewById<TextView>(R.id.tv_temperatura_actual)
        val tvBadgeEstado = findViewById<TextView>(R.id.tv_badge_estado)
        val tvServidorVal = findViewById<TextView>(R.id.tv_servidor_val)
        val tvEstadoRangoVal = findViewById<TextView>(R.id.tv_estado_rango_val)
        val tvUltimaLecturaVal = findViewById<TextView>(R.id.tv_ultima_lectura_val)
        val chartTemperatura = findViewById<TemperaturaChartView>(R.id.chart_temperatura)
        val tvMinVal = findViewById<TextView>(R.id.tv_min_val)
        val tvPromVal = findViewById<TextView>(R.id.tv_prom_val)
        val tvMaxVal = findViewById<TextView>(R.id.tv_max_val)

        val titulo = intent.getStringExtra("EXTRA_TITULO") ?: "Sala 2"
        val servidor = intent.getStringExtra("EXTRA_SERVIDOR") ?: "SRV-B"
        val tempActual = intent.getStringExtra("EXTRA_TEMP_ACTUAL") ?: "29.6°C"
        val badgeTexto = intent.getStringExtra("EXTRA_BADGE_TEXTO") ?: "ADVERTENCIA"
        val badgeTipo = intent.getStringExtra("EXTRA_BADGE_TIPO") ?: "warning"
        val estadoRango = intent.getStringExtra("EXTRA_ESTADO_RANGO") ?: "Advertencia (27 a 32 °C)"
        val ultimaLectura = intent.getStringExtra("EXTRA_ULTIMA_LECTURA") ?: "14:32:05"
        val min = intent.getStringExtra("EXTRA_MIN") ?: "23.9°C"
        val prom = intent.getStringExtra("EXTRA_PROM") ?: "27.1°C"
        val max = intent.getStringExtra("EXTRA_MAX") ?: "29.9°C"
        val chartData = intent.getFloatArrayExtra("EXTRA_CHART_DATA") ?: floatArrayOf(0.2f, 0.35f, 0.6f, 0.55f, 0.7f, 0.68f)

        tvTituloSala.text = titulo
        tvServidorVal.text = servidor
        tvTemperaturaActual.text = tempActual
        tvBadgeEstado.text = badgeTexto
        tvEstadoRangoVal.text = estadoRango
        tvUltimaLecturaVal.text = ultimaLectura
        tvMinVal.text = min
        tvPromVal.text = prom
        tvMaxVal.text = max
        chartTemperatura.dataPoints = chartData

        when (badgeTipo) {
            "ok" -> {
                tvBadgeEstado.setBackgroundResource(R.drawable.bg_badge_ok)
                tvBadgeEstado.setTextColor(ContextCompat.getColor(this, R.color.status_ok))
                tvTemperaturaActual.setTextColor(ContextCompat.getColor(this, R.color.status_ok))
                tvEstadoRangoVal.setTextColor(ContextCompat.getColor(this, R.color.status_ok))
            }
            "critical" -> {
                tvBadgeEstado.setBackgroundResource(R.drawable.bg_badge_critical)
                tvBadgeEstado.setTextColor(ContextCompat.getColor(this, R.color.status_critical))
                tvTemperaturaActual.setTextColor(ContextCompat.getColor(this, R.color.status_critical))
                tvEstadoRangoVal.setTextColor(ContextCompat.getColor(this, R.color.status_critical))
            }
            else -> {
                tvBadgeEstado.setBackgroundResource(R.drawable.bg_badge_warning)
                tvBadgeEstado.setTextColor(ContextCompat.getColor(this, R.color.status_warning))
                tvTemperaturaActual.setTextColor(ContextCompat.getColor(this, R.color.status_warning))
                tvEstadoRangoVal.setTextColor(ContextCompat.getColor(this, R.color.status_warning))
            }
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}