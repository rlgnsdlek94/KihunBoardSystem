package com.example.kbs.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CanvasView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var paint: Paint = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 8f
    }

    private var path: Path = Path()
    private var bitmap: Bitmap? = null
    private var canvas: Canvas? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 기존 그린 내용과 현재 경로를 그림
        this.canvas?.drawPath(path, paint)
        canvas.drawBitmap(bitmap!!, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(x, y)
            MotionEvent.ACTION_MOVE -> path.lineTo(x, y)
            MotionEvent.ACTION_UP -> canvas?.drawPath(path, paint).also { path.reset() }
        }
        invalidate()
        return true
    }

    fun clear() {
        path.reset()
        canvas?.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR)
        invalidate()
    }

    fun getBitmap(): Bitmap? = bitmap
}