package com.example.fredliao.codelibrary.router

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.example.fredliao.codelibrary.edittext.PrefixSuffixEditTextViewModel
import com.example.fredliao.codelibrary.numberpicker.NumberPickerViewModel
import com.example.fredliao.codelibrary.recyclerview.RecyclerViewModel

class ViewModelFactory(private val application: Application, private val intent: Intent) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val modelCls = modelClass
        return when {
            modelCls.isAssignableFrom(RecyclerViewModel::class.java) ->
                RecyclerViewModel()
            modelCls.isAssignableFrom(NumberPickerViewModel::class.java) ->
                NumberPickerViewModel()
            modelCls.isAssignableFrom(PrefixSuffixEditTextViewModel::class.java) ->
                PrefixSuffixEditTextViewModel()

            else ->
                throw IllegalArgumentException("Unknow ViewModel class" + modelCls)
        } as T
    }

}