package com.example.fredliao.codelibrary.router

import android.content.Context
import android.content.Intent
import com.example.fredliao.codelibrary.camera.CameraActivity
import com.example.fredliao.codelibrary.edittext.PrefixSuffixEditTextActivity
import com.example.fredliao.codelibrary.login.LoginActivity
import com.example.fredliao.codelibrary.numberpicker.NumberPickerActivity
import com.example.fredliao.codelibrary.recyclerview.RecyclerViewActivity
import com.example.fredliao.codelibrary.spinner.SpinnerActivity

/*
object class this a simple singleton in kotlin
 */
object MasterRouter {

    enum class Extras

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

    fun startPrefixSuffixEditTextActivity(context: Context): Intent {
        val intent = Intent(context, PrefixSuffixEditTextActivity::class.java)
        return intent
    }

    fun startLoginActivity(context: Context): Intent {
        return Intent(context, LoginActivity::class.java)
    }

    fun startSpinnerActivity(context: Context): Intent {
        return Intent(context, SpinnerActivity::class.java)
    }
}