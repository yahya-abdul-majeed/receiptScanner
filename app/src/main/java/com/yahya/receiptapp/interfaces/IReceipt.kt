package com.yahya.receiptapp.interfaces

import com.google.mlkit.vision.text.Text

interface IReceipt {
    fun execute(visionText: Text): ArrayList<String>
}