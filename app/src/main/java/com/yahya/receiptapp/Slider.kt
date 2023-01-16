package com.yahya.receiptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.yahya.receiptapp.databinding.ActivitySliderBinding
import com.yahya.receiptapp.interfaces.ISliderInterface
import com.yahya.receiptapp.utility.IntroSlide
import com.yahya.receiptapp.utility.SliderAdapter

class Slider : AppCompatActivity(),ISliderInterface {

    private lateinit var viewBinding: ActivitySliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val list = listOf<IntroSlide>(
            IntroSlide(R.drawable.imageslider1,"DETECT PRODUCTS BY SCANNING RECEIPTS"),
            IntroSlide(R.drawable.imageslider2,"ADD AND DELETE PRODUCTS"),
            IntroSlide(R.drawable.imageslider3,"RECEIVE NOTIFICATIONS BEFORE EXPIRY DATES")
        )

        var viewPager = viewBinding.mViewPager
        viewPager.adapter = SliderAdapter(list,this)


    }

    override fun onSkipBtnClick() {
        startActivity(Intent(this,MainActivity::class.java))
    }
}