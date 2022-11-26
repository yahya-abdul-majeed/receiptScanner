package com.yahya.receiptapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.yahya.receiptapp.databinding.ActivityMainBinding
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var uri:Uri
    private lateinit var tempImageFilePath:String



    private val selectImageFromGalleryResult = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { binding.imageViewReceipt.setImageURI(uri) }
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
    }

    private fun createImageFile(): File {
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("temp_image",".jpg",storageDir)
    }


}