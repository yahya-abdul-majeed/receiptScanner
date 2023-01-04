 package com.yahya.receiptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yahya.receiptapp.databinding.ActivityPurchasedItemsBinding


 class PurchasedItemsActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityPurchasedItemsBinding

    private lateinit var listOfProducts: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPurchasedItemsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        listOfProducts = intent.getStringArrayListExtra("itemsPurchased") as ArrayList<String>
        val itemListAdapter = ItemListAdapter(listOfProducts)
        viewBinding.recyclerview.adapter = itemListAdapter
        viewBinding.recyclerview.layoutManager = LinearLayoutManager(this)
    }


}