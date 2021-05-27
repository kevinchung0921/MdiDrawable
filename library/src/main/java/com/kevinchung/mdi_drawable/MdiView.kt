package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class MdiView:TextView {

    private val config: MdiDrawableConfig

    constructor(ctx:Context):this(ctx, null)
    constructor(ctx:Context, attrs:AttributeSet?):this(ctx, attrs, 0)
    constructor(ctx:Context, attrs:AttributeSet?, style:Int):super(ctx, attrs, style) {

        typeface = ResourcesCompat.getFont(ctx, R.font.mdi)
        config = initConfig(attrs)

        background = generateBackground()
    }

    private fun initConfig(attrs: AttributeSet?): MdiDrawableConfig {
        var conf = MdiDrawableConfig()

        attrs?.let {
            val a = context?.theme?.obtainStyledAttributes(
                    attrs,
                    R.styleable.MdiView,
                    0, 0)

            a?.let {
                conf.enableGradient = it.getBoolean(R.styleable.MdiView_useGradient, conf.enableBackground)
                conf.cornerRadius = it.getDimensionPixelSize(R.styleable.MdiView_cornerRadius, conf.cornerRadius)

                conf.backgroundColor = it.getColor(R.styleable.MdiView_bgColor, conf.backgroundColor)

                conf.gradientStartColor = it.getColor(R.styleable.MdiView_gradientStartColor, conf.gradientStartColor)
                conf.gradientEndColor = it.getColor(R.styleable.MdiView_gradientEndColor, conf.gradientEndColor)

                conf.gradientOrientation = when(it.getInt(R.styleable.MdiView_gradientOrientation, conf.gradientOrientation.ordinal)) {

                    GradientDrawable.Orientation.TOP_BOTTOM.ordinal -> GradientDrawable.Orientation.TOP_BOTTOM
                    GradientDrawable.Orientation.BOTTOM_TOP.ordinal -> GradientDrawable.Orientation.BOTTOM_TOP
                    GradientDrawable.Orientation.TR_BL.ordinal -> GradientDrawable.Orientation.TR_BL
                    GradientDrawable.Orientation.TL_BR.ordinal -> GradientDrawable.Orientation.TL_BR
                    GradientDrawable.Orientation.BR_TL.ordinal -> GradientDrawable.Orientation.BR_TL
                    GradientDrawable.Orientation.BL_TR.ordinal -> GradientDrawable.Orientation.BL_TR
                    GradientDrawable.Orientation.LEFT_RIGHT.ordinal -> GradientDrawable.Orientation.LEFT_RIGHT
                    GradientDrawable.Orientation.RIGHT_LEFT.ordinal -> GradientDrawable.Orientation.RIGHT_LEFT
                    else -> GradientDrawable.Orientation.TOP_BOTTOM

                }

                conf.strokeColor = it.getColor(R.styleable.MdiView_strokeColor, conf.strokeColor)
                conf.strokeWidth = it.getDimensionPixelSize(R.styleable.MdiView_strokeWidth, conf.strokeWidth)
                conf.strokeDashGap = it.getDimension(R.styleable.MdiView_strokeGap, conf.strokeDashGap)
                conf.strokeDashWidth = it.getDimension(R.styleable.MdiView_strokeGap, conf.strokeDashWidth)
                it.recycle()
            }
        }
        return conf
    }

    private fun generateBackground():GradientDrawable? {
        var drawable = GradientDrawable()

        if(config.enableGradient) {
            drawable = GradientDrawable(
                    config.gradientOrientation,
                    intArrayOf(config.gradientStartColor,config.gradientEndColor)
            )
        } else {
            drawable.setColor(config.backgroundColor)
        }
        drawable.cornerRadius = config.cornerRadius.toFloat()
        drawable.setStroke(
                config.strokeWidth,
                config.strokeColor,
                config.strokeDashWidth,
                config.strokeDashGap
        )
        return drawable
    }
}