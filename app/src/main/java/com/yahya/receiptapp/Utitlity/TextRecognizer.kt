package com.yahya.receiptapp.Utitlity

import android.content.Context
import android.net.Uri
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.yahya.receiptapp.Receipts.BasicReceipt
import com.yahya.receiptapp.interfaces.IReceipt

class TextRecognizer {
    public var dictionary = mapOf( //immutable
        "milk" to 5,
        "banana" to 7,
        "apple" to 4,
        "chicken" to 10,
        "cheese" to 3,
        "egg" to 12,
        "bread" to 2,
        "pepsi" to 5,
        "juice" to 6,
        "mushroom" to 7,
        "flour" to 25,
        "onion" to 7,
        "Tomato" to 8
    )

    public fun recognizeText(uri:Uri, context: Context, callback:(ArrayList<String>)->Unit){
        var itemsPurchased = ArrayList<String>()

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
        val image = InputImage.fromFilePath(context,uri);
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                itemsPurchased = receiptProcessor(BasicReceipt(),visionText)
                callback(itemsPurchased)
            }
            .addOnFailureListener { e ->

            }

    }

    private fun receiptProcessor(receipt: IReceipt, visionText: Text): ArrayList<String>{
        return receipt.execute(visionText);
    }
}