package com.yahya.receiptapp.utility

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.yahya.receiptapp.models.Product
import java.io.BufferedReader
import java.io.File

class PersistenceService {
    companion object{
        fun saveItemStoreToInternalStorage(context: Context, store: ArrayList<Product>, filename: String){
            val gson = GsonBuilder().create()
            val fileContents = gson.toJson(store)

            context.openFileOutput(filename, Context.MODE_PRIVATE).use{
                it.write(fileContents.toByteArray())
            }
        }

        fun getItemStoreFromInternalStorage(context: Context, filename: String): ArrayList<Product>{
            val path: String = context.filesDir.absolutePath + "/" + filename
            val file = File(path)
            if(file.exists()){
                val gson = GsonBuilder().create()
                val allText = context.openFileInput(filename).bufferedReader().use(BufferedReader::readText)
                return gson.fromJson(allText, object : TypeToken<ArrayList<Product>>(){}.type)
            }
            return ArrayList()
        }
    }
}