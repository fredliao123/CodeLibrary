package com.example.fredliao.codelibrary.spinner;

import android.databinding.InverseMethod;

public class SpinnerItemBindingAdapter {

    @InverseMethod("toStringArrayIndex")
    public static String toStringArrayItem(String[] array, int pos) {
        return (array != null && array.length > 0) ? array[pos] : "";
    }

    public static int toStringArrayIndex(String[] arr, String value) {
        if (value == null) {
            return 0;
        }
        for (int i = 0; i < arr.length; i++) {
            if (value.equals(arr[i])) {
                return i;
            }
        }
        return 0;
    }
}
