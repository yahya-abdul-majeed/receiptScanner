package com.yahya.receiptapp.utility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.yahya.receiptapp.R
import com.yahya.receiptapp.interfaces.ISliderInterface


class SliderAdapter(var introslides: List<IntroSlide>,val listener: ISliderInterface): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_page, parent, false)
        return SliderViewHolder(view);
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(introslides[position])
    }

    override fun getItemCount(): Int = introslides.size;

    inner class SliderViewHolder(view:View): ViewHolder(view) {
        //initialize views here
        val iv = view.findViewById<ImageView>(R.id.imageView2)
        val tv = view.findViewById<TextView>(R.id.textViewSlider)
        val btn = view.findViewById<Button>(R.id.btnSkip)
        init{
            btn.setOnClickListener {
                listener.onSkipBtnClick()
            }
        }
        fun bind(introslide: IntroSlide){
            iv.setImageResource(introslide.icon )
            tv.text = introslide.text
        }
    }
}