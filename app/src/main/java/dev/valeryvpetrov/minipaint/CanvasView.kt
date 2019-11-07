package dev.valeryvpetrov.minipaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.res.ResourcesCompat

private const val STROKE_WIDTH = 12f

class CanvasView(context: Context) : View(context) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val backgroundColor = ResourcesCompat
        .getColor(resources, R.color.colorBackground, null)
    private val paintColor = ResourcesCompat
        .getColor(resources, R.color.colorPaint, null)

    private val paint = Paint()

    init {
        setupPaint(paint)
    }

    private fun setupPaint(paint: Paint) {
        paint.color = paintColor
        paint.isAntiAlias = true    // Smooths out edges of what is drawn without affecting shape
        paint.isDither = true    // Dithering affects how colors with higher-precision than the device are down-sampled
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = STROKE_WIDTH
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()  // free up memory

        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)    // create new
        extraCanvas = Canvas(extraBitmap)

        extraCanvas.drawColor(backgroundColor)  // fill background
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }
}