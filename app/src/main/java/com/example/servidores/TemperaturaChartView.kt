package com.example.servidores

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class TemperaturaChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#5AA9FF")
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    private val dottedRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#EF4444")
        strokeWidth = 3f
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
    }

    private val dottedOrangePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F59E0B")
        strokeWidth = 3f
        style = Paint.Style.STROKE
        pathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)
    }

    private val textRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#EF4444")
        textSize = 28f
        isFakeBoldText = true
    }

    private val textOrangePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F59E0B")
        textSize = 28f
        isFakeBoldText = true
    }

    private val path = Path()
    private val fillPath = Path()

    var dataPoints = floatArrayOf(0.2f, 0.35f, 0.6f, 0.55f, 0.7f, 0.68f)
        set(value) {
            field = value
            invalidate()
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w > 0 && h > 0) {
            fillPaint.shader = LinearGradient(
                0f, 40f, 0f, h.toFloat(),
                Color.parseColor("#335AA9FF"), Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val w = width.toFloat()
        val h = height.toFloat()
        if (w == 0f || h == 0f) return

        val paddingLeft = 10f
        val paddingRight = 10f
        val paddingTop = 40f
        val paddingBottom = 20f

        val graphWidth = w - paddingLeft - paddingRight
        val graphHeight = h - paddingTop - paddingBottom

        // Línea punteada 32 °C
        val y32 = paddingTop + graphHeight * 0.2f
        canvas.drawText("32 °C", paddingLeft, y32 - 10f, textRedPaint)
        canvas.drawLine(paddingLeft, y32, w - paddingRight, y32, dottedRedPaint)

        // Línea punteada 27 °C
        val y27 = paddingTop + graphHeight * 0.55f
        canvas.drawText("27 °C", paddingLeft, y27 - 10f, textOrangePaint)
        canvas.drawLine(paddingLeft, y27, w - paddingRight, y27, dottedOrangePaint)

        if (dataPoints.isEmpty()) return

        path.reset()
        fillPath.reset()

        val stepX = graphWidth / (dataPoints.size - 1)

        val firstX = paddingLeft
        val firstY = paddingTop + (1f - dataPoints[0]) * graphHeight
        path.moveTo(firstX, firstY)
        fillPath.moveTo(firstX, h)
        fillPath.lineTo(firstX, firstY)

        for (i in 0 until dataPoints.size - 1) {
            val x1 = paddingLeft + i * stepX
            val y1 = paddingTop + (1f - dataPoints[i]) * graphHeight
            val x2 = paddingLeft + (i + 1) * stepX
            val y2 = paddingTop + (1f - dataPoints[i + 1]) * graphHeight

            val cx = (x1 + x2) / 2f
            path.cubicTo(cx, y1, cx, y2, x2, y2)
            fillPath.cubicTo(cx, y1, cx, y2, x2, y2)
        }

        val lastX = paddingLeft + (dataPoints.size - 1) * stepX
        fillPath.lineTo(lastX, h)
        fillPath.close()

        canvas.drawPath(fillPath, fillPaint)
        canvas.drawPath(path, linePaint)
    }
}