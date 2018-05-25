package com.example.fredliao.codelibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.fredliao.codelibrary.router.MasterRouter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_main)
    }

    fun jumpToRecyclerViewActivity(v : View) {
        startActivity(MasterRouter.startRecyclerViewActivity(this))
    }

    fun jumpToNumberPickerActivity(v : View) {
        startActivity(MasterRouter.startNumberPickerActivity(this))
    }
}