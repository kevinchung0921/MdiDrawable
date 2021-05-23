package com.kevinchung.mdi_drawable_demo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.kevinchung.mdi_drawable.DrawableConfig
import com.kevinchung.mdi_drawable.MdiDrawableBuilder
import kotlinx.android.synthetic.main.dialog.*
import org.greenrobot.eventbus.EventBus

class AddDialog(context:Context): Dialog(context) {

    private val drawableConfig = DrawableConfig()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)

        switchBackground.setOnCheckedChangeListener { _, isChecked ->
            layoutBackground.visibility = if(isChecked) View.VISIBLE else View.GONE
            drawableConfig.enableBackground = isChecked
            drawableConfig.enableGradient = true
            updateIcon()
        }

        setEdits()

        setButton()
    }

    private fun setEdits() {

        editSize.setSimpleTextChangeWatcher { theNewText, _ ->
            try {
                drawableConfig.size = theNewText.toInt()
                updateIcon()
            } catch(e:Exception) {}
        }

        editColor.endIconImageButton.setOnClickListener {
            var color = Color.BLACK
            try {
                color = exeditColor.text.toString().toInt()

            } catch(e:Exception){}

            ColorPickerDialog
                .Builder(context)
                .setDefaultColor(color)
                .setColorListener { i, s ->
                    exeditColor.setText(i.toString())
                }
                .show()
        }

        editColor.setSimpleTextChangeWatcher { theNewText, isError ->
            try {
                drawableConfig.iconColor = theNewText.toInt()
                updateIcon()
            } catch(e:Exception){}
        }

        editBgColorStart.endIconImageButton.setOnClickListener {
            var color = Color.BLACK
            try {
                color = exeditBgColorStart.text.toString().toInt()

            } catch(e:Exception){}

            ColorPickerDialog
                .Builder(context)
                .setDefaultColor(color)
                .setColorListener { i, s ->
                    exeditBgColorStart.setText(i.toString())
                }
                .show()
        }

        editBgColorStart.setSimpleTextChangeWatcher { theNewText, isError ->
            try {
                drawableConfig.gradientStartColor = theNewText.toInt()
                updateIcon()
            } catch(e:Exception){}
        }

        editBgColorEnd.endIconImageButton.setOnClickListener {
            var color = Color.BLACK
            try {
                color = exeditBgColorEnd.text.toString().toInt()

            } catch(e:Exception){}

            ColorPickerDialog
                .Builder(context)
                .setDefaultColor(color)
                .setColorListener { i, s ->
                    exeditBgColorEnd.setText(i.toString())
                }
                .show()
        }

        editBgColorEnd.setSimpleTextChangeWatcher { theNewText, isError ->
            try {
                drawableConfig.gradientEndColor = theNewText.toInt()
                updateIcon()
            } catch(e:Exception){}
        }

        editName.setSimpleTextChangeWatcher { theNewText, _ ->
            try {
                val builder = MdiDrawableBuilder(context)

                val id = context.resources.getIdentifier("mdi_$theNewText", "string", context.packageName)

                builder.config = drawableConfig

                if(id > 0) {
                    drawableConfig.stringId = id
                    updateIcon()
                } else null
            } catch(e:Exception){}
        }

        editRadius.setSimpleTextChangeWatcher { theNewText, _ ->
            try {
                drawableConfig.cornerRadius = theNewText.toInt()
                updateIcon()
            } catch (e:Exception) {}
        }


    }

    private fun setButton() {
        btnCancel.setOnClickListener {
            dismiss()
        }

        btnOk.setOnClickListener {

            EventBus.getDefault().post(drawableConfig)

            dismiss()
        }
    }


    private fun updateIcon() {
        ivDrawable.background = MdiDrawableBuilder(context, drawableConfig).create()
    }
}