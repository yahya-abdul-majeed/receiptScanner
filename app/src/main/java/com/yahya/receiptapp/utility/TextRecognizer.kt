package com.yahya.receiptapp.utility

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.yahya.receiptapp.receipts.BasicReceipt
import com.yahya.receiptapp.interfaces.IReceipt
import com.yahya.receiptapp.models.Product

class TextRecognizer {

    val foodAPI = FoodAPI()
    public fun recognizeText(uri:Uri, context: Context, callback:(ArrayList<Product>)->Unit){
        var itemsPurchased: ArrayList<Product>

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromFilePath(context,uri);
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                itemsPurchased = receiptProcessor(BasicReceipt(),visionText)
                itemsPurchased = foodAPI.findExpiryDate(itemsPurchased)
                callback(itemsPurchased)
            }
            .addOnFailureListener { e ->

            }

    }

    private fun receiptProcessor(receipt: IReceipt, visionText: Text): ArrayList<Product>{
        return receipt.execute(visionText);
    }
}