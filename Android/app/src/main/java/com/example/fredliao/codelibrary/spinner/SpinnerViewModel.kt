package com.example.fredliao.codelibrary.spinner

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.example.fredliao.codelibrary.BR
import com.example.fredliao.codelibrary.common.BaseViewModel
import com.example.fredliao.codelibrary.network.data.CodeLibRepository

class SpinnerViewModel(val codeLibRepository: CodeLibRepository) : BaseViewModel() {
    val spinnerItems: ObservableList<SpinnerItem> = ObservableArrayList<SpinnerItem>()

    var state: String = ""
        @Bindable
        get() = field
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.state)
        }

    init{
        getSpinnerItems()
    }

    fun getSpinnerItems() {
        codeLibRepository.getSpinnerItem()
            .subscribe({
                spinnerItems.addAll(it)
            })
    }

}