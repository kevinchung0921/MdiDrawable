package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.TextView

class MdiDrawableBuilder(private val context:Context) {

    var config = DrawableConfig()


    constructor(context:Context, config:DrawableConfig):this(context) {
        this.config = config
    }

    fun stringId(id: Int) = apply {
        config.stringId = id
    }

    fun backgroundColor(color: Int) = apply {
        config.backgroundColor = color
    }

    fun iconColor(color: Int) = apply {
        config.iconColor = color
    }

    fun size(px: Int) = apply {
        config.size = px
    }

    fun sizeDp(dp: Int) = apply {
        config.size =
            (dp* context.resources.displayMetrics.densityDpi/DisplayMetrics.DENSITY_DEFAULT)
    }

    fun enableBackground(enable: Boolean = true) = apply {
        config.enableBackground = enable
    }

    fun useGradient(
        startColor:Int,
        endColor:Int,
        orientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM
    ) = apply {
        config.enableGradient = true
        config.gradientStartColor = startColor
        config.gradientEndColor = endColor
        config.gradientOrientation = orientation
    }

    fun disableGradient() = apply {
        config.enableGradient = false
    }

    fun radius(radius: Int) = apply {
        config.cornerRadius = radius
    }

    fun setPadding(padding: Int) = apply {
        config.padding = padding
    }

    fun setStroke(
        width: Int,
        color: Int = Color.BLACK,
        length: Float = 10f,
        gap: Float = 0f
    ) = apply {
        config.strokeWidth = width
        config.strokeColor = color
        config.strokeDashWidth = length
        config.strokeDashGap = gap
    }


    fun create(stringId: Int = config.stringId):Drawable? {
        try {
            config.stringId = stringId
            // get material font
            context.let {
                Log.d("MDID", config.showConfig())
                val tv = TextView(it)

                tv.setTextColor(config.iconColor)


                tv.typeface = Typeface.createFromAsset(it.assets, "mdifont.ttf")
                tv.textSize = config.size.toFloat()/3
                tv.text = it.getString(config.stringId)
                tv.gravity = Gravity.CENTER
                tv.measure(
                    View.MeasureSpec.makeMeasureSpec(config.size+2* config.padding, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(config.size+2* config.padding, View.MeasureSpec.EXACTLY)
                )
                if(config.enableBackground)
                    tv.background = generateBackground()
                tv.setPadding(config.padding, config.padding, config.padding, config.padding)
                tv.layout(0,0,config.size+2* config.padding, config.size+2* config.padding)

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
        drawable.cornerRadius = config.cornerRadius.toFloat()
        drawable.setStroke(config.strokeWidth, config.strokeColor, config.strokeDashWidth, config.strokeDashGap)
        if(config.enableGradient) {
            drawable.gradientType = config.gradientOrientation.ordinal
            drawable.colors = intArrayOf(config.gradientStartColor,config.gradientEndColor)
        } else {
            drawable.setColor(config.backgroundColor)
        }
        return drawable
    }

}