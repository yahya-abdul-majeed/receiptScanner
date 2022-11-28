package com.yahya.receiptapp

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.drawToBitmap
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.yahya.receiptapp.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var uri:Uri
    private lateinit var tempImageFilePath:String




    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { binding.imageViewReceipt.setImageURI(uri) }
        this.uri = uri!!
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSaved ->
        if (isSaved)
        {
            binding.imageViewReceipt.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnGallery.setOnClickListener{
            selectImageFromGalleryResult.launch("image/*")
        }
        binding.btnCamera.setOnClickListener{
            uri = FileProvider.getUriForFile(this,"com.yahya.receiptapp.provider", createImageFile().also {
                tempImageFilePath = it.absolutePath
            })
            takePicture.launch(uri)
        }
        binding.btnExecute.setOnClickListener{
            recognizeText()
        }

    }

    private fun createImageFile(): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image",".jpg",storageDir)
    }

    private fun recognizeText(){
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        val image = InputImage.fromFilePath(this,uri);
        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                binding.textView.text = visionText.text
                // Task completed successfully
                // [START_EXCLUDE]
                // [START get_text]
//                for (block in visionText.textBlocks) {
//                    val boundingBox = block.boundingBox
//                    val cornerPoints = block.cornerPoints
//                    val text = block.text
//
//                    for (line in block.lines) {
//                        // ...
//                        for (element in line.elements) {
//                            // ...
//                        }
//                    }
//                }
                // [END get_text]
                // [END_EXCLUDE]
            }
            .addOnFailureListener { e ->

            }


    }

    private fun processTextBlock(result: Text) {
        // [START mlkit_process_text_block]
        val resultText = result.text
        for (block in result.textBlocks) {
            val blockText = block.text
            val blockCornerPoints = block.cornerPoints
            val blockFrame = block.boundingBox
            for (line in block.lines) {
                val lineText = line.text
                val lineCornerPoints = line.cornerPoints
                val lineFrame = line.boundingBox
                for (element in line.elements) {
                    val elementText = element.text
                    val elementCornerPoints = element.cornerPoints
                    val elementFrame = element.boundingBox
                }
            }
        }
        // [END mlkit_process_text_block]
    }


}