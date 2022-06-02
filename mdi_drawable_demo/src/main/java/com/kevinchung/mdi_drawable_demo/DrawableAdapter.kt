package com.kevinchung.mdi_drawable_demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kevinchung.mdi_drawable.MdiDrawableConfig


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
            holder.onBindView(context, configList[position])
        }
    }

    override fun getItemCount(): Int {
        return configList.size
    }

}

class DrawableViewHolder(view:View):RecyclerView.ViewHolder(view){

    private var image: ImageView = view.findViewById(R.id.ivDrawable)
    private var desc: TextView = view.findViewById(R.id.tvDesc)

    fun onBindView(context:Context, config:MdiDrawableConfig) {
        image.background = config.create(context)
        desc.text = config.toString()
    }
}

