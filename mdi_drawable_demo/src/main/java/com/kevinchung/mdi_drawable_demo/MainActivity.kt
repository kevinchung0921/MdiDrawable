package com.kevinchung.mdi_drawable_demo

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinchung.mdi_drawable.MdiDrawableConfig
import com.kevinchung.mdi_drawable.MdiDrawable
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {

    private val drawableList = ArrayList<MdiDrawableConfig>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        EventBus.getDefault().register(this)

        setFab()
        setRecyclerView()
    }

    private fun setFab() {
        fab.setImageDrawable(MdiDrawable(this)
            .stringId(R.string.mdi_plus)
            .create())

        // show AddDialog for adding custom icon
        fab.setOnClickListener {
            AddDialog(this).show()
        }
    }

    private fun setRecyclerView() {

        setDataList()

        rvDrawables.layoutManager = LinearLayoutManager(this)
        val adapter = DrawableAdapter(this, drawableList)
        rvDrawables.adapter = adapter
    }

    // receive configuration object from AddDialog and insert to list
    @Subscribe(threadMode=ThreadMode.MAIN)
    fun onAddDrawable(config:MdiDrawableConfig) {
        drawableList.add(0, config)
        // add at first position
        rvDrawables.adapter?.notifyItemInserted(0)
    }


    // create demo icons
    private fun setDataList() {

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_atom_variant,
                        iconColor = resources.getColor(R.color.blue_A200)
                )
        )
        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_bullseye_arrow,
                        iconColor = resources.getColor(R.color.red_300)
                )
        )
        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_coffee,
                        iconColor = resources.getColor(R.color.cyan_A700)
                )
        )
        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_collage,
                        iconColor = resources.getColor(R.color.red_500)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_format_wrap_inline,
                        iconColor = resources.getColor(R.color.deep_orange_A200)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_emoticon_cool,
                        size = 48,
                        iconColor = resources.getColor(R.color.green_900)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_emoticon_happy,
                        size = 200,
                        iconColor = resources.getColor(R.color.teal_300)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_car,
                        enableBackground = true,
                        strokeColor = resources.getColor(R.color.grey_900),
                        backgroundColor = resources.getColor(R.color.blue_grey_600),
                        iconColor = resources.getColor(R.color.vordiplom_0)
                )
        )
        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_card_account_details,
                        enableBackground = true,
                        strokeColor = resources.getColor(R.color.blue_grey_400),
                        strokeWidth = 6,
                        cornerRadius = 6,
                        padding = 6,
                        iconColor = resources.getColor(R.color.brown_500)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_devices,
                        enableBackground = true,
                        strokeColor = resources.getColor(R.color.purple_300),
                        strokeWidth = 8,
                        strokeDashGap = 5f,
                        cornerRadius = 6,
                        padding = 12,
                        iconColor = resources.getColor(R.color.deep_orange_400)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_chart_bar_stacked,
                        enableBackground = true,
                        cornerRadius = 6,
                        iconColor = Color.WHITE,
                        enableGradient = true,
                        gradientStartColor = Color.LTGRAY,
                        gradientEndColor = Color.DKGRAY
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_check_decagram,
                        enableBackground = true,
                        cornerRadius = 100,
                        iconColor = Color.WHITE,
                        backgroundColor = resources.getColor(R.color.green_800),
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_checkerboard,
                        enableBackground = true,
                        cornerRadius = 100,
                        padding = 4,
                        iconColor = Color.WHITE,
                        enableGradient = true,
                        gradientStartColor = resources.getColor(R.color.light_blue_900),
                        gradientEndColor = resources.getColor(R.color.light_blue_100),
                        gradientOrientation = GradientDrawable.Orientation.TL_BR
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_desktop_mac,
                        enableBackground = true,
                        cornerRadius = 30,
                        padding = 6,
                        iconColor = resources.getColor(R.color.joyful_2),
                        backgroundColor = resources.getColor(R.color.joyful_1)
                )
        )
    }
}