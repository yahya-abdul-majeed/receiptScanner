package com.yahya.receiptapp.utility

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.yahya.receiptapp.models.Product
import java.util.*

class FoodAPI {
    public val dictionary = mapOf<String,Int>( //immutable
        "milk" to 5,
        "bananas" to 7,
        "apples" to 4,
        "chicken" to 10,
        "cheese" to 3,
        "eggs" to 12,
        "bread" to 2,
        "pepsi" to 5,
        "juice" to 6,
        "mushrooms" to 7,
        "flour" to 25,
        "onions" to 7,
        "Tomatoes" to 8
    )

    fun findExpiryDate(listOfProducts:ArrayList<Product>):ArrayList<Product>{
        val calendar = Calendar.getInstance()
        for(product in listOfProducts){
            if(dictionary[product.name.lowercase()] != null){
                var days: Int? = dictionary[product.name.lowercase()]
                product.expiryDate = addDay(product.dateBought,days!!)

            }else{
                product.expiryDate = null
            }

        }
        return listOfProducts
    }

    fun addDay(date: Date?, i: Int): Date? {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DAY_OF_YEAR, i)
        return cal.time
    }

    fun addMonth(date: Date?, i: Int): Date? {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.MONTH, i)
        return cal.time
    }

    fun addYear(date: Date?, i: Int): Date? {
        val cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.YEAR, i)
        return cal.time
    }
}