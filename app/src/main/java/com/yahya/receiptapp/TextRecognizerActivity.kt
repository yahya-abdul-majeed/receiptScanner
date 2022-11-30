package com.yahya.receiptapp

import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.yahya.receiptapp.databinding.ActivityTextRecognizerBinding


class TextRecognizerActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityTextRecognizerBinding
    private lateinit var uri:Uri

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding= ActivityTextRecognizerBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.textView.movementMethod = ScrollingMovementMethod()
        uri = Uri.parse(intent.getStringExtra("imageuri"))
        viewBinding.imageView.setImageURI(uri)
        recognizeText()
    }

    private fun recognizeText(){
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromFilePath(this,uri);
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                viewBinding.textView.text = visionText.text
            }
            .addOnFailureListener { e ->

            }


    }
}