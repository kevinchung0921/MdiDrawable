package com.kevinchung.mdi_drawable_demo

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinchung.mdi_drawable.MdiDrawableConfig
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {

    private val drawableList = ArrayList<MdiDrawableConfig>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        EventBus.getDefault().register(this)

        setFab()
        setRecyclerView()
    }

    private fun setFab() {
        fab.setImageDrawable(MdiDrawableConfig()
            .stringId(R.string.mdi_plus)
            .create(this))

        // show AddDialog for adding custom icon
        fab.setOnClickListener {
            AddDialog(this).show()
        }
    }

    private fun setRecyclerView() {

        setDataList()

        rvDrawables.layoutManager = GridLayoutManager(this,5)
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
            MdiDrawableConfig()
                .stringId(R.string.mdi_account_circle_outline)
                .iconColor(ContextCompat.getColor(this, R.color.blue_grey_400))
        )

        drawableList.add(
            MdiDrawableConfig()
                .stringId(R.string.mdi_apple_ios)
                .iconGradientStartColor(ContextCompat.getColor(this, R.color.black))
                .iconGradientEndColor(ContextCompat.getColor(this, R.color.grey_300))
                .iconGradientOrientation(GradientDrawable.Orientation.LEFT_RIGHT)
        )

        drawableList.add(
            MdiDrawableConfig()
                .stringId(R.string.mdi_apps)
                .iconColor(ContextCompat.getColor(this, R.color.blue_200))
        )

        drawableList.add(
            MdiDrawableConfig()
                .stringId(R.string.mdi_badge_account_horizontal)
                .iconColor(ContextCompat.getColor(this, R.color.blue_800))
        )

        drawableList.add(
            MdiDrawableConfig()
                .size(120)
                .stringId(R.string.mdi_airplane)
                .iconColor(ContextCompat.getColor(this, R.color.blue_grey_800))
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_coffee,
                        iconColor = ContextCompat.getColor(this,R.color.cyan_A700),
                        shadowColor = ContextCompat.getColor(this,R.color.grey_600),
                        shadowDx = 4f,
                        shadowDy = 4f,
                        shadowRadius = 8f
                )
        )
        drawableList.add(
                MdiDrawableConfig()
                    .stringId(R.string.mdi_collage)
                    .iconColor(ContextCompat.getColor(this,R.color.red_900))
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_format_wrap_inline,
                        iconColor = ContextCompat.getColor(this,R.color.deep_orange_A200)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_emoticon_sad,
                        size = 50,
                        iconColor = ContextCompat.getColor(this,R.color.green_900)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_emoticon_happy,
                        size = 200,
                        iconColor = ContextCompat.getColor(this,R.color.teal_300)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_car,
                        strokeColor = ContextCompat.getColor(this,R.color.grey_900),
                        backgroundColor = ContextCompat.getColor(this,R.color.blue_grey_600),
                        iconColor = ContextCompat.getColor(this,R.color.green_900),
                        shadowColor = ContextCompat.getColor(this,R.color.white),
                        cornerRadius = 12,
                        shadowRadius = 1f,
                        shadowDy = 4f,
                        shadowDx = 4f
                )
        )
        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_card_account_details,
                        strokeColor = ContextCompat.getColor(this,R.color.blue_grey_400),
                        strokeWidth = 3,
                        cornerRadius = 6,
                        padding = 6,
                        iconColor = ContextCompat.getColor(this,R.color.brown_500)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_devices,
                        strokeColor = ContextCompat.getColor(this,R.color.purple_300),
                        strokeWidth = 8,
                        strokeDashGap = 5f,
                        cornerRadius = 6,
                        padding = 12,
                        iconColor = ContextCompat.getColor(this,R.color.deep_orange_400)
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_chart_bar_stacked,
                        cornerRadius = 6,
                        iconColor = Color.WHITE,
                        bgGradientStartColor = Color.LTGRAY,
                        bgGradientEndColor = Color.DKGRAY
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_check_decagram,
                        cornerRadius = 100,
                        iconColor = Color.WHITE,
                        padding = 12,
                        backgroundColor = ContextCompat.getColor(this,R.color.green_800),
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_checkerboard,
                        cornerRadius = 100,
                        padding = 12,
                        iconColor = Color.WHITE,
                        bgGradientStartColor = ContextCompat.getColor(this,R.color.light_blue_900),
                        bgGradientEndColor = ContextCompat.getColor(this,R.color.light_blue_100),
                        bgGradientOrientation = GradientDrawable.Orientation.TL_BR
                )
        )

        drawableList.add(
                MdiDrawableConfig(
                        stringId = R.string.mdi_desktop_mac,
                        cornerRadius = 30,
                        padding = 16,
                        iconColor = ContextCompat.getColor(this,R.color.joyful_2),
                        backgroundColor = ContextCompat.getColor(this,R.color.joyful_3)
                )
        )
    }
}