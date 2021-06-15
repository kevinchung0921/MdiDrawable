package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable

class MdiDrawableConfig(
    // basic settings
    var iconString: String? = "",
    var stringId: Int = R.string.mdi_android,
    var iconColor: Int = Color.BLACK,
    var size: Int = 100,
    var padding: Int = 0,

    // background settings
    var enableBackground: Boolean = false,
    var cornerRadius: Int = 0,
    var backgroundColor: Int = Color.TRANSPARENT,
    var enableGradient: Boolean = false,
    var gradientStartColor: Int = Color.LTGRAY,
    var gradientEndColor: Int = Color.DKGRAY,
    var gradientOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.TOP_BOTTOM,

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
    fun showConfig():String {
        val sb = StringBuilder()
        sb.append("id:[${Integer.toHexString(stringId)}] size [$size], padding [$padding], color:[${Integer.toHexString(iconColor)}] ")
        if(enableBackground) {
            sb.append("gradient [$enableGradient], radius [$cornerRadius], stroke [$strokeWidth] ")
        }
        if(shadowColor!=Color.TRANSPARENT && (shadowDx!=0f || shadowDy!=0f)) {
            sb.append(", shadow radius[$shadowRadius] dx[$shadowDx] dy[$shadowDy] color[${Integer.toHexString(shadowColor)}]")
        }
        return sb.toString()
    }
}