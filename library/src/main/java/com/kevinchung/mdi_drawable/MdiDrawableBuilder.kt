package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView

class MdiDrawableBuilder {

    companion object {

        private var context: Context? = null
        private var stringId: Int = R.string.mdi_android
        private var backgroundColor: Int = Color.TRANSPARENT
        private var foregroundColor: Int = Color.BLACK
        private var cornerRadius: Int = 0
        private var size: Int = 64
        private var padding: Int = 0
        private var enableBackground = false
        private var enableGradient = false
        private var gradientStartColor = Color.LTGRAY
        private var gradientEndColor = Color.DKGRAY
        private var gradientOrientation = GradientDrawable.Orientation.TOP_BOTTOM


        fun withContext(context: Context) = apply { this.context = context }

        fun backgroundColor(color: Int) = apply {
            this.backgroundColor = color
        }

        fun foregroundColor(color: Int) = apply {
            this.foregroundColor = color
        }

        fun size(px: Int) = apply {
            this.size = px
        }

        fun enableBackground(enable: Boolean = true) = apply {
            this.enableBackground = enable
        }

        fun useGradient(
            startColor:Int,
            endColor:Int,
            orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM
        ) = apply {
            enableGradient = true
            this.gradientStartColor = startColor
            this.gradientEndColor = endColor
            this.gradientOrientation = orientation
        }

        fun disableGradient() = apply {
            enableGradient = false
        }

        fun radius(radius: Int) = apply {
            cornerRadius = radius
        }

        fun setPadding(padding: Int) = apply {
            this.padding = padding
        }

        fun create(stringId: Int = this.stringId):Drawable? {
            try {
                // get material font
                context?.let {
                    val tv = TextView(it)
                    tv.setTextColor(this.foregroundColor)

                    tv.typeface = Typeface.createFromAsset(it.assets, "mdifont.ttf")
                    tv.textSize = this.size.toFloat()/3
                    tv.text = it.getString(this.stringId)
                    tv.gravity = Gravity.CENTER
                    tv.measure(
                        View.MeasureSpec.makeMeasureSpec(this.size+2* padding, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(this.size+2* padding, View.MeasureSpec.EXACTLY)
                    )
                    if(enableBackground)
                        tv.background = generateBackground()
                    tv.setPadding(padding, padding, padding, padding)
                    tv.layout(0,0,this.size+2* padding, this.size+2* padding)

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

        private fun generateBackground():GradientDrawable? {
            val drawable = GradientDrawable()
            drawable.cornerRadius = cornerRadius.toFloat()
            if(enableGradient) {
                drawable.gradientType = gradientOrientation.ordinal
                drawable.colors = intArrayOf(gradientStartColor,gradientEndColor)
            } else {
                drawable.setColor(backgroundColor)
            }
            return drawable
        }
    }
}