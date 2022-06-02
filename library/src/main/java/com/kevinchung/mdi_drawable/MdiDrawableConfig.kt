package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View

class MdiDrawableConfig(

    // basic settings
    var iconString: String? = "",
    var stringId: Int = -1,
    var iconColor: Int = Color.BLACK,
    var size: Int = 100,
    var padding: Int = 0,

    // icon gradient color
    var iconGradientStartColor: Int = Color.BLACK,
    var iconGradientEndColor: Int = Color.BLACK,
    var iconGradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM,

    // background settings
    var backgroundColor: Int = Color.TRANSPARENT,
    var cornerRadius: Int = 0,
    var bgGradientStartColor: Int = Color.TRANSPARENT,
    var bgGradientEndColor: Int = Color.TRANSPARENT,
    var bgGradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM,

    // stroke settings
    var strokeColor: Int = Color.BLACK,
    var strokeWidth: Int = 0,
    var strokeDashWidth: Float = 10f,
    var strokeDashGap: Float = 0f,

    // shadow settings
    var shadowColor: Int = Color.TRANSPARENT,
    var shadowRadius: Float = 0f,
    var shadowDx: Float = 0f,
    var shadowDy: Float = 0f

) {
    init {
        // background color set at construction, apply to gradient color
        if(backgroundColor != Color.TRANSPARENT) {
            bgGradientStartColor = backgroundColor
            bgGradientEndColor = backgroundColor
        }
        // icon color set at construction, apply to gradient color
        if(iconColor != Color.BLACK) {
            iconGradientStartColor = iconColor
            iconGradientEndColor = iconColor
        }

    }
    fun iconString(icon:String?) = apply { this.iconString = icon }
    fun stringId(id: Int) = apply { this.stringId =id }
    fun iconColor(color: Int) = apply {
        this.iconColor = color
        this.iconGradientStartColor = color
        this.iconGradientEndColor = color
    }
    fun iconGradientStartColor(color: Int) = apply { this.iconGradientStartColor = color }
    fun iconGradientEndColor(color: Int) = apply { this.iconGradientEndColor = color }
    fun iconGradientOrientation(orientation: GradientDrawable.Orientation) = apply { this.iconGradientOrientation = orientation }

    fun size(size: Int) = apply { this.size = size }
    fun padding(padding: Int) = apply { this.padding = padding }
    fun cornerRadius(radius: Int) = apply { this.cornerRadius = radius}
    fun backgroundColor(color: Int) = apply {
        this.bgGradientStartColor = color
        this.bgGradientEndColor = color
    }
    fun bgGradientStartColor(color: Int) = apply { this.bgGradientStartColor = color }
    fun bgGradientEndColor(color: Int) = apply { this.bgGradientEndColor = color }
    fun bgGradientOrientation(orientation: GradientDrawable.Orientation) = apply { this.bgGradientOrientation = orientation }
    fun strokeColor(color: Int) = apply { this.strokeColor = color }
    fun strokeWidth(width: Int) = apply { this.strokeWidth = width }
    fun strokeDashWidth(width: Float) = apply { this.strokeDashWidth = width }
    fun strokeDashGap(gap: Float) = apply { this.strokeDashGap = gap }
    fun shadowColor(color: Int) = apply { this.shadowColor = color }
    fun shadowRadius(radius: Float) = apply { this.shadowRadius = radius }
    fun shadowDx(dx: Float) = apply { this.shadowDx = dx }
    fun shadowDy(dy: Float) = apply { this.shadowDy = dy }

    override
    fun toString():String {
        val sb = StringBuilder()
        sb.append("string id[${Integer.toHexString(stringId)}] size[$size] padding[$padding] color[${Integer.toHexString(iconColor)}] ")
        sb.append("bg start[${Integer.toHexString(bgGradientStartColor)}] end[${Integer.toHexString(bgGradientEndColor)}] radius[$cornerRadius] stroke [$strokeWidth]")
        sb.append(", shadow radius[$shadowRadius] dx[$shadowDx] dy[$shadowDy] color[${Integer.toHexString(shadowColor)}]")
        return sb.toString()
    }

    fun create(context: Context): Drawable? {
        try {
            context.let {
                val tv = MdiView(it)
                tv.configuration = this

                tv.measure(
                    View.MeasureSpec.makeMeasureSpec(size+2* padding, View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(size+2* padding, View.MeasureSpec.EXACTLY)
                )
                tv.layout(0,0,size+2* padding, size+2* padding)


                val bitmap = Bitmap.createBitmap(
                    tv.width,
                    tv.height,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                tv.draw(canvas)
                return BitmapDrawable(it.resources, bitmap)
            }
        } catch (e: Exception) {
            Log.e("MDID",e.toString())
        }
        return null
    }
}