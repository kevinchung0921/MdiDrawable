package com.kevinchung.mdi_drawable_demo

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.kevinchung.mdi_drawable.*
/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        val tv: TextView = view.findViewById(R.id.textview_first)
        val tv2: TextView = view.findViewById(R.id.textview_2)
        context?.let {
            val builder = MdiDrawableBuilder
                .withContext(it)
                .enableBackground(true)
                .useGradient(Color.BLUE, Color.GREEN, GradientDrawable.Orientation.TOP_BOTTOM)
                .foregroundColor(Color.WHITE)
                .size(96)
                .radius(10)
                .setPadding(10)

            tv.background = builder
                .create(R.string.mdi_id_card)

            tv2.background = builder.setPadding(0).create(R.string.mdi_account)
        }


    }
}