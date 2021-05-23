package com.kevinchung.mdi_drawable_demo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevinchung.mdi_drawable.DrawableConfig
import com.kevinchung.mdi_drawable.MdiDrawableBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {

    val drawableList = ArrayList<DrawableConfig>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        EventBus.getDefault().register(this)

        setFab()
        setRecyclerView()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setFab() {
        fab.setImageDrawable(MdiDrawableBuilder(this)
            .stringId(R.string.mdi_plus)
            .create())

        fab.setOnClickListener {
            AddDialog(this).show()
        }
    }

    private fun setRecyclerView() {

        drawableList.add(
                DrawableConfig(
                        stringId = R.string.mdi_star,
                        size = 100,
                        iconColor = Color.DKGRAY
                )
        )

        drawableList.add(
                DrawableConfig(
                        stringId = R.string.mdi_monitor,
                        size = 60,
                        iconColor = Color.LTGRAY
                )
        )

        drawableList.add(
                DrawableConfig(
                        stringId = R.string.mdi_account,
                        size = 90,
                        iconColor = Color.BLUE
                )
        )

        drawableList.add(
                DrawableConfig(
                        stringId = R.string.mdi_cash,
                        size = 240,
                        iconColor = Color.GREEN
                )
        )

        rvDrawables.layoutManager = LinearLayoutManager(this)
        val adapter = DrawableAdapter(this, drawableList)
        rvDrawables.adapter = adapter
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    fun onAddDrawable(config:DrawableConfig) {
        drawableList.add(0, config)
        rvDrawables.adapter?.notifyItemInserted(0)
    }

}