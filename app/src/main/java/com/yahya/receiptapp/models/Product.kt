package com.yahya.receiptapp.models

import java.util.Date

data class Product(
    val name: String,
    val dateBought: Date,
    val expiryDate:Date,
):java.io.Serializable{

}