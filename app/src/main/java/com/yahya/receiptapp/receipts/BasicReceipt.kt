package com.yahya.receiptapp.receipts

import com.google.mlkit.vision.text.Text
import com.yahya.receiptapp.interfaces.IReceipt

class BasicReceipt: IReceipt {
    override fun execute(visionText: Text): ArrayList<String> {
        var list = ArrayList<String>()
        for(block in visionText.textBlocks){
            for(line in block.lines){
                list.add(line.text);
            }
        }
        return list
    }
}