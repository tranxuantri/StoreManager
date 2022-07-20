package com.example.myapplication.utility

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class BarcodeAnalyzer(private val context: Context) : ImageAnalysis.Analyzer {

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val img = image.image
        if (img != null) {
            val inputImage = InputImage.fromMediaImage(img, image.imageInfo.rotationDegrees)

            val option = BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS, Barcode.FORMAT_AZTEC).build()
            val scanner = BarcodeScanning.getClient(option)
            scanner.process(inputImage).addOnSuccessListener { barcode ->
                if (barcode.isNotEmpty()) {
                    Toast.makeText(context, "barcode = $barcode", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { }

            image.close()
        }
    }
}