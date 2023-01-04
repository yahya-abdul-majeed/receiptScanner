 package com.yahya.receiptapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yahya.receiptapp.utility.ItemListAdapter
import com.yahya.receiptapp.databinding.ActivityPurchasedItemsBinding
import com.yahya.receiptapp.interfaces.IRecyclerViewInterface


 class PurchasedItemsActivity : AppCompatActivity(),IRecyclerViewInterface, AddItemDialogFragment.AddItemDialogListener {
    private lateinit var viewBinding: ActivityPurchasedItemsBinding
    private lateinit var listOfProducts: ArrayList<String>
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPurchasedItemsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        listOfProducts = intent.getStringArrayListExtra("itemsPurchased") as ArrayList<String>
        itemListAdapter = ItemListAdapter(listOfProducts,this)
        recyclerView = viewBinding.recyclerview
        recyclerView.adapter = itemListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewBinding.btnAdditem.setOnClickListener {
            AddItemDialogFragment().show(supportFragmentManager,"additem")
        }
    }

     override fun onItemLongClick(position: Int) {
         listOfProducts.removeAt(position)
         itemListAdapter.notifyItemRemoved(position)
     }

     override fun onDialogPositiveClick(dialog: DialogFragment, itemAdded: String) {
         listOfProducts.add(itemAdded)
         itemListAdapter.notifyDataSetChanged()
     }



 }