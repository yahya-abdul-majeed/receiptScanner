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
import com.yahya.receiptapp.utility.ItemListAdapter
import java.io.BufferedReader
import java.io.File


class ItemStoreActivity : AppCompatActivity(),IRecyclerViewInterface {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemListAdapter: ItemListAdapter
    private lateinit var viewBinding: ActivityItemStoreBinding
    private var itemStore = ArrayList<String>()
    private var filename = "itemStore"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityItemStoreBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        var productsReceived = intent.getStringArrayListExtra("listofproducts")  as ArrayList<String>
        getItemStoreFromInternalStorage()
        itemStore.addAll(productsReceived)
        saveToInternalStorage(itemStore)
        itemListAdapter = ItemListAdapter(itemStore,this)
        recyclerView = viewBinding.storeRecyclerView
        recyclerView.adapter = itemListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun saveToInternalStorage(store: ArrayList<String>){
        val gson = GsonBuilder().create()
        val fileContents = gson.toJson(store)

        this.openFileOutput(filename,Context.MODE_PRIVATE).use{
            it.write(fileContents.toByteArray())
        }
    }

    private fun getItemStoreFromInternalStorage(){
        val path: String = filesDir.absolutePath + "/" + filename
        val file = File(path)
        if(file.exists()){
            val gson = GsonBuilder().create()
            val allText = this.openFileInput(filename).bufferedReader().use(BufferedReader::readText)
            itemStore = gson.fromJson(allText, object : TypeToken<ArrayList<String>>(){}.type)
        }

    }

    override fun onItemLongClick(position: Int) {}
}