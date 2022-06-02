package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.graphics.drawable.GradientDrawable
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue




object UIUtils {
    fun dpToPx(dp: Double, context: Context?): Int {
        return if (context != null) {
            val resources = context.resources
            val px: Double = dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
            px.toInt()
        } else {
            dp.toInt()
        }
    }

    fun spToPx(sp: Float, context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            context.resources.displayMetrics
        ).toInt()
    }

    fun generateBackground(
        config: MdiDrawableConfig
    ): GradientDrawable {
        val drawable = GradientDrawable(
            config.bgGradientOrientation,

            intArrayOf(
                config.bgGradientStartColor,
                config.bgGradientEndColor
            )
        )

        drawable.cornerRadius = config.cornerRadius.toFloat()

        drawable.setStroke(
            config.strokeWidth,
            config.strokeColor,
            config.strokeDashWidth,
            config.strokeDashGap
        )
        return drawable
    }

    fun generateShader(
        config: MdiDrawableConfig
    ): LinearGradient {
        var x1 = 0f
        var y1 = 0f
        var x2 = 0f
        var y2 = 0f
        val size = config.size.toFloat()
        Log.d("util","size:$size")
        when(config.iconGradientOrientation) {
            GradientDrawable.Orientation.TOP_BOTTOM ->
                y2 = size
            GradientDrawable.Orientation.BOTTOM_TOP ->
                y1 = size
            GradientDrawable.Orientation.TR_BL -> {
                x1 = size
                y2 = size
            }
            GradientDrawable.Orientation.TL_BR -> {
                x2 = size
                y2 = size
            }
            GradientDrawable.Orientation.BR_TL -> {
                x1 = size
                y1 = size
            }
            GradientDrawable.Orientation.BL_TR -> {
                y1 = size
                x2 = size
            }
            GradientDrawable.Orientation.LEFT_RIGHT -> {
                x2 = size
            }
            GradientDrawable.Orientation.RIGHT_LEFT -> {
                x1 = size
            }
            else -> {
                y2 = size
            }
        }
        return LinearGradient(
            x1,y1,x2,y2,
            intArrayOf(config.iconGradientStartColor, config.iconGradientEndColor),
            floatArrayOf(0.1f, 0.8f),
            Shader.TileMode.CLAMP
        )
    }
}