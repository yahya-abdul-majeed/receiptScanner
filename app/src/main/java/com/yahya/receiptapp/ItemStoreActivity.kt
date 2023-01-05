package com.yahya.receiptapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.yahya.receiptapp.databinding.ActivityItemStoreBinding
import com.yahya.receiptapp.interfaces.IRecyclerViewInterface
import com.yahya.receiptapp.models.Product
import com.yahya.receiptapp.utility.ItemListAdapter
import com.yahya.receiptapp.utility.PersistenceService
import java.io.BufferedReader
import java.io.File


class ItemStoreActivity : AppCompatActivity(),IRecyclerViewInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var viewBinding: ActivityItemStoreBinding
    private var itemStore = ArrayList<Product>()
    private var filename = "itemStore"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityItemStoreBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var productsReceived = intent.getStringArrayListExtra("listofproducts")  as ArrayList<Product>
        itemStore = PersistenceService.getItemStoreFromInternalStorage(this, filename)
        itemStore.addAll(productsReceived)
        PersistenceService.saveItemStoreToInternalStorage(this,itemStore, filename)
        itemListAdapter = ItemListAdapter(itemStore,this)
        recyclerView = viewBinding.storeRecyclerView
        recyclerView.adapter = itemListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewBinding.btnClearStore.setOnClickListener {
            itemStore.clear()
            itemListAdapter.notifyDataSetChanged()
            PersistenceService.saveItemStoreToInternalStorage(this,itemStore,filename)
        }

    }



    override fun onItemLongClick(position: Int) {}
}