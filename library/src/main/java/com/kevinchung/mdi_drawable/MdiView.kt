package com.kevinchung.mdi_drawable

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import androidx.core.content.res.ResourcesCompat

class MdiView:androidx.appcompat.widget.AppCompatTextView {
    companion object {
        private const val TAG = "MdiView"

    }
    var configuration: MdiDrawableConfig
        set(value) {
            field = value
            setupConfigure(value)
            invalidate()
        }

    constructor(ctx:Context):this(ctx, null)
    constructor(ctx:Context, attrs:AttributeSet?):this(ctx, attrs, 0)
    constructor(ctx:Context, attrs:AttributeSet?, style:Int):super(ctx, attrs, style) {
        typeface = ResourcesCompat.getFont(ctx, R.font.mdi)
        configuration = initConfig(attrs)
    }

    private fun initConfig(attrs: AttributeSet?): MdiDrawableConfig {
        val conf = MdiDrawableConfig()

        attrs?.let {
            val a = context?.theme?.obtainStyledAttributes(
                    attrs,
                    R.styleable.MdiView,
                    0,
                0
            )

            a?.let {
                conf.iconString = it.getString(R.styleable.MdiView_android_text)
                conf.iconColor = it.getColor(R.styleable.MdiView_android_textColor, conf.iconColor)
                conf.size = it.getDimensionPixelSize(R.styleable.MdiView_android_textSize, conf.size)
                conf.shadowRadius = it.getFloat(R.styleable.MdiView_android_shadowRadius, conf.shadowRadius)
                conf.shadowDx = it.getFloat(R.styleable.MdiView_android_shadowDx, conf.shadowDx)
                conf.shadowDy = it.getFloat(R.styleable.MdiView_android_shadowDy, conf.shadowDy)
                conf.shadowColor = it.getColor(R.styleable.MdiView_android_shadowColor, conf.shadowColor)
                conf.padding = it.getDimensionPixelSize(R.styleable.MdiView_android_padding, conf.padding)
                conf.backgroundColor = it.getColor(R.styleable.MdiView_bgColor, Color.TRANSPARENT)

                conf.cornerRadius = it.getDimensionPixelSize(R.styleable.MdiView_cornerRadius, conf.cornerRadius)

                // use background color for gradient color if attributes not set
                conf.bgGradientStartColor = it.getColor(R.styleable.MdiView_bgGradientStartColor, conf.backgroundColor)
                conf.bgGradientEndColor = it.getColor(R.styleable.MdiView_bgGradientEndColor, conf.backgroundColor)

                conf.bgGradientOrientation = when(it.getInt(R.styleable.MdiView_bgGradientOrientation, conf.bgGradientOrientation.ordinal)) {

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

                // set icon gradient colors as iconColor if attributes not set
                conf.iconGradientStartColor = it.getColor(R.styleable.MdiView_iconGradientStartColor, conf.iconColor)
                conf.iconGradientEndColor = it.getColor(R.styleable.MdiView_iconGradientEndColor, conf.iconColor)

                conf.iconGradientOrientation = when(it.getInt(R.styleable.MdiView_iconGradientOrientation, conf.iconGradientOrientation.ordinal)) {

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

    private fun setupConfigure(
        config: MdiDrawableConfig
    ) {
        setTextColor(config.iconColor)
         if(config.iconGradientStartColor != config.iconGradientEndColor)
            paint.shader = UIUtils.generateShader(config)

        textSize = config.size.toFloat()/UIUtils.spToPx(1.5f, context).toFloat()

        text = if(config.stringId != -1)
            context.getString(config.stringId)
        else
            config.iconString

        gravity = Gravity.CENTER

        background = UIUtils.generateBackground(config)

        setShadowLayer(config.shadowRadius, config.shadowDx, config.shadowDy, config.shadowColor)

        setPadding(config.padding, config.padding, config.padding, config.padding)

    }
}