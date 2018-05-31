package com.example.fredliao.codelibrary.edittext

import android.databinding.Bindable
import com.example.fredliao.codelibrary.common.BaseViewModel
import com.example.fredliao.codelibrary.BR
import timber.log.Timber

class PrefixSuffixEditTextViewModel: BaseViewModel() {

    var minimumRate: String = ""
    @Bindable
    get() = field
    @Bindable
    set(value) {
        field = value
        Timber.i(field, field)
    }

    /**
     * Note this is how to define a bindable variable that will notice UI to change when changed
     */
    var rateEmpty = true
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.rateEmpty)
        }

    var minimumRateFocused = false
        @Bindable
        get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.minimumRateFocused)
        }
}