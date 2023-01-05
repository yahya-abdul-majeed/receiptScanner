package com.yahya.receiptapp.receipts

import com.google.mlkit.vision.text.Text
import com.yahya.receiptapp.interfaces.IReceipt
import com.yahya.receiptapp.models.Product
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class BasicReceipt: IReceipt {
    override fun execute(visionText: Text): ArrayList<Product> {
        var list = ArrayList<Product>()
        for(block in visionText.textBlocks){
            for(line in block.lines){
                list.add(Product(line.text, Date(),Date()));
            }
        }
        return list
    }
}