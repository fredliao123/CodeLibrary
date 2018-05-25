package com.example.fredliao.codelibrary.router

import android.content.Context
import android.content.Intent
import com.example.fredliao.codelibrary.camera.CameraActivity
import com.example.fredliao.codelibrary.numberpicker.NumberPickerActivity
import com.example.fredliao.codelibrary.recyclerview.RecyclerViewActivity

/*
object class this a simple singleton in kotlin
 */
object MasterRouter {

    enum class Extras {

    }

    fun startCameraActivity(context: Context): Intent {
        val intent = Intent(context, CameraActivity::class.java)
        return intent
    }

    fun startRecyclerViewActivity(context: Context): Intent {
        val intent = Intent(context, RecyclerViewActivity::class.java)
        return intent
    }

    fun startNumberPickerActivity(context: Context): Intent {
        val intent = Intent(context, NumberPickerActivity::class.java)
        return intent
    }

}