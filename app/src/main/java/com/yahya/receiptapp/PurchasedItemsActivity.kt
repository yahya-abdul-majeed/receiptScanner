 package com.yahya.receiptapp

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yahya.receiptapp.utility.ItemListAdapter
import com.yahya.receiptapp.databinding.ActivityPurchasedItemsBinding
import com.yahya.receiptapp.interfaces.IRecyclerViewInterface
import com.yahya.receiptapp.models.Product
import com.yahya.receiptapp.utility.NotificationHelper
import com.yahya.receiptapp.utility.channelId


 class PurchasedItemsActivity : AppCompatActivity(),IRecyclerViewInterface, AddItemDialogFragment.AddItemDialogListener {
    private lateinit var viewBinding: ActivityPurchasedItemsBinding
    private lateinit var listOfProducts: ArrayList<Product>
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var recyclerView: RecyclerView

    init{

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPurchasedItemsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        listOfProducts = intent.getStringArrayListExtra("itemsPurchased") as ArrayList<Product>
        itemListAdapter = ItemListAdapter(listOfProducts,this)
        recyclerView = viewBinding.recyclerview
        recyclerView.adapter = itemListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewBinding.btnAdditem.setOnClickListener {
            AddItemDialogFragment().show(supportFragmentManager,"additem")
        }
        viewBinding.btnAddtostore.setOnClickListener {
            val intent = Intent(this,ItemStoreActivity::class.java)
            intent.putExtra("listofproducts", listOfProducts)
            startActivity(intent)
        }
    }


     override fun onItemLongClick(position: Int) {
         listOfProducts.removeAt(position)
         itemListAdapter.notifyItemRemoved(position)
     }

     @SuppressLint("NotifyDataSetChanged")
     override fun onDialogPositiveClick(dialog: DialogFragment, itemAdded: Product) {
         listOfProducts.add(itemAdded)
         itemListAdapter.notifyDataSetChanged()
     }



 }