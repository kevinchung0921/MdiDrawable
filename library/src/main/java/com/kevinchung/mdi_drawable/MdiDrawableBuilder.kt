package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView

class MdiDrawableBuilder {

    companion object {
        private var context: Context? = null
        private var iconId: Int = R.string.mdi_android
        private var background: Int = Color.TRANSPARENT
        private var foreground: Int = Color.BLACK
        private var cornerRadius: FloatArray? = null
        private var iconSize: Int = 32
        private var size: Int = 64
        private var padding: Float = 0f

        fun with(context: Context) = apply { this.context = context }

        fun iconId(id: Int) = apply {
            this.iconId = id
        }



        fun create():Drawable? {
            try {
                // get material font
                context?.let {
                    val tv = TextView(it)
                    tv.setTextColor(this.foreground)
                    tv.setBackgroundColor(this.background)
                    tv.typeface = Typeface.createFromAsset(it.assets, "mdifont.ttf")
                    tv.textSize = this.iconSize.toFloat()
                    tv.text = it.getString(this.iconId)
                    tv.gravity = Gravity.CENTER
                    tv.measure(
                        View.MeasureSpec.makeMeasureSpec(this.size, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(this.size, View.MeasureSpec.EXACTLY)
                    )
                    tv.layout(0,0,this.size, this.size)
                    tv.invalidate()

                    val bitmap = Bitmap.createBitmap(tv.width, tv.height, Bitmap.Config.ARGB_8888)
                    var canvas = Canvas(bitmap)
                    tv.draw(canvas)
                    return BitmapDrawable(it.resources, bitmap)
                }
            } catch (e: Exception) {
                Log.e("MDID",e.toString())
            }
            return null
        }
    }
}