package com.yahya.receiptapp.models



import android.app.AlarmManager
import java.util.Date

data class Product(
    var name: String,
    var dateBought: Date,
    var expiryDate:Date? = null,
):java.io.Serializable{


}