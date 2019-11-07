package dev.valeryvpetrov.minipaint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.core.content.res.ResourcesCompat

class CanvasView(context: Context) : View(context) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val backgroundColor = ResourcesCompat
        .getColor(resources, R.color.colorBackground, null)

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