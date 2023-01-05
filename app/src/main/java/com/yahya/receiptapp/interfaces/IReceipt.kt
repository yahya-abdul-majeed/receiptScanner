package com.yahya.receiptapp.interfaces

import com.google.mlkit.vision.text.Text
import com.yahya.receiptapp.models.Product

interface IReceipt {
    fun execute(visionText: Text): ArrayList<Product>
}