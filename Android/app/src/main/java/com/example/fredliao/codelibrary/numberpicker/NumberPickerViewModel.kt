package com.example.fredliao.codelibrary.numberpicker

import android.databinding.Bindable
import com.example.fredliao.codelibrary.BR
import com.example.fredliao.codelibrary.common.BaseViewModel

/**
 * Remember to implement PickerInterfaceViewModel
 */
class NumberPickerViewModel : BaseViewModel(), PickerInterfaceViewModel {

    override var numberPickerValue: Int = 22
        @Bindable
        get() = field
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.numberPickerValue)
        }
}