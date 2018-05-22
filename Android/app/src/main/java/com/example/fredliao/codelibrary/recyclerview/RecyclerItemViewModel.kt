package com.example.fredliao.codelibrary.recyclerview

import android.databinding.Bindable
import com.example.fredliao.codelibrary.common.BaseViewModel

class RecyclerItemViewModel(val recyclerItem: RecyclerItem) : BaseViewModel() {

    val title: String
        @Bindable
        get() = recyclerItem.title

}