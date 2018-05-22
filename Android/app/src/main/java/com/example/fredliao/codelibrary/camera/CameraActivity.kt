package com.example.fredliao.codelibrary.camera

import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : AppCompatActivity() {

    companion object {
        private val REQUEST_TAKE_PHOTO = 1
    }

    /*
    Start camera and store picture in a local temp position
     */
    fun startCamera() {
        val intent = Intent()
        val file = createImageFile(this)
        file?.let {
            //The com.supp.photoid.fileprovider is a Provider defined in manifest
            val photoURI = FileProvider.getUriForFile(this, "com.example.fredliao.codelibrary.photoid.fileprovider", it)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(intent, REQUEST_TAKE_PHOTO)
        }
    }

    private fun createImageFile(context: Context): File? {
        try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val imageFileName = "JPEG_" + timeStamp + "_"
            val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                context.cacheDir      /* directory */
            )
            return image
        } catch (e: IOException) {

        }
        return null
    }
}