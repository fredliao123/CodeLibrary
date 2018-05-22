package com.example.fredliao.codelibrary.recyclerview

import com.example.fredliao.codelibrary.common.BaseViewModel

class RecyclerViewModel : BaseViewModel() {

    val items = arrayListOf(
        RecyclerItemViewModel(RecyclerItem("test1")),
        RecyclerItemViewModel(RecyclerItem("test2")),
        RecyclerItemViewModel(RecyclerItem("test3"))
    )
}