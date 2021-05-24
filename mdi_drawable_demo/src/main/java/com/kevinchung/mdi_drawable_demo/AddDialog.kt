package com.kevinchung.mdi_drawable_demo

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.kevinchung.mdi_drawable.DrawableConfig
import com.kevinchung.mdi_drawable.MdiDrawable
import kotlinx.android.synthetic.main.dialog.*
import org.greenrobot.eventbus.EventBus
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

class AddDialog(context:Context): Dialog(context) {

    companion object {
        private val TAG = "AddDialog"
    }

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
        updateIcon()
    }

    private fun loadAsset() {
        val mdiArray = context.resources.getStringArray(R.array.mdi_names)
        val adapter = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, mdiArray)
        exeditName.setAdapter(adapter)
    }

    private fun setEdits() {


        loadAsset()

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

            showColorPicker(color, exeditColor)
        }

        exeditColor.setText(Integer.toHexString(drawableConfig.iconColor))

        editColor.setSimpleTextChangeWatcher { theNewText, isError ->
            try {
                drawableConfig.iconColor = theNewText.toLong(radix = 16).toInt()
                updateIcon()
            } catch(e:Exception){
                Log.d(TAG,e.toString())
            }
        }

        editBgColorStart.endIconImageButton.setOnClickListener {
            var color = Color.BLACK
            try {
                color = exeditBgColorStart.text.toString().toLong(radix = 16).toInt()
            } catch(e:Exception){
                Log.d(TAG,e.toString())
            }
            showColorPicker(color, exeditBgColorStart)
        }

        editBgColorStart.setSimpleTextChangeWatcher { theNewText, isError ->
            try {
                drawableConfig.gradientStartColor = theNewText.toLong(radix = 16).toInt()
                updateIcon()
            } catch(e:Exception){}
        }

        editBgColorEnd.endIconImageButton.setOnClickListener {
            var color = Color.BLACK
            try {
                color = exeditBgColorEnd.text.toString().toLong(radix = 16).toInt()

            } catch(e:Exception){}
            showColorPicker(color, exeditBgColorEnd)
        }

        editBgColorEnd.setSimpleTextChangeWatcher { theNewText, isError ->
            try {
                drawableConfig.gradientEndColor = theNewText.toLong(radix = 16).toInt()
                updateIcon()
            } catch(e:Exception){}
        }

        editName.setSimpleTextChangeWatcher { theNewText, _ ->
            try {
                val builder = MdiDrawable(context)

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


    private fun showColorPicker(color:Int, edit:ExtendedEditText) {
        ColorPickerDialog
            .Builder(context)
            .setDefaultColor(color)
            .setColorListener { _, s ->
                edit.setText(s.replace("#","ff"))
            }
            .show()
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
        ivDrawable.background = MdiDrawable(context, drawableConfig).create()
    }
}