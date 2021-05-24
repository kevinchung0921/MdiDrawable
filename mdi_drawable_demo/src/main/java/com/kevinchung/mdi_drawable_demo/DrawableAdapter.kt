package com.kevinchung.mdi_drawable_demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinchung.mdi_drawable.MdiDrawableConfig
import com.kevinchung.mdi_drawable.MdiDrawable

class DrawableAdapter(
        private val context: Context,
        private val configList:ArrayList<MdiDrawableConfig>
): RecyclerView.Adapter<DrawableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrawableViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return DrawableViewHolder(view)
    }

    override fun onBindViewHolder(holder: DrawableViewHolder, position: Int) {
        if(position in configList.indices) {
            holder.onBindView(configList[position])
        }
    }

    override fun getItemCount(): Int {
        return configList.size
    }

}

class DrawableViewHolder(private val view:View):RecyclerView.ViewHolder(view){

    private var image: ImageView = view.findViewById(R.id.ivDrawable)
    private var desc: TextView = view.findViewById(R.id.tvDesc)

    fun onBindView(config:MdiDrawableConfig) {
        val builder = MdiDrawable(view.context)
        builder.config = config
        image.background = builder.create()
        desc.text = config.showConfig()
    }
}

