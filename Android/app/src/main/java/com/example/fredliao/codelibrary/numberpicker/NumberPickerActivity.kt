package com.example.fredliao.codelibrary.numberpicker

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.InputType
import android.view.View
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.common.BaseActivity
import com.example.fredliao.codelibrary.databinding.ActivityNumberPickerBinding
import com.example.fredliao.codelibrary.databinding.DialogNumberPickerBinding
import com.example.fredliao.codelibrary.router.ViewModelFactory
import kotlinx.android.synthetic.main.activity_number_picker.*

class NumberPickerActivity : BaseActivity<NumberPickerViewModel>() {

    val binding: ActivityNumberPickerBinding by lazy {
        DataBindingUtil.setContentView<ActivityNumberPickerBinding>(this, R.layout.activity_number_picker)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = setupViewModel()
        initEditText()
    }

    private fun initEditText() {
        //This line will prevent EditText has focus
        number_picker_edit_text.inputType = InputType.TYPE_NULL
        number_picker_edit_text.setOnFocusChangeListener {
            v: View, hasFocus: Boolean ->
            if(hasFocus) {
                showNumberPicker(v)
            }
        }
    }

    private fun setupViewModel() : NumberPickerViewModel{
        val factory = ViewModelFactory(application, intent)
        viewModel = ViewModelProviders.of(this,factory).get(NumberPickerViewModel::class.java)
        return viewModel
    }

    fun showNumberPicker(v: View) {
        val dialogDataBinding: DialogNumberPickerBinding = DataBindingUtil.inflate(this.layoutInflater, R.layout.dialog_number_picker, null, false)
        dialogDataBinding.viewModel = binding.viewModel
        val np = dialogDataBinding.numberPicker
        //Public Integers should also put in resource files
        np.maxValue = this.resources.getInteger(R.integer.max_rate)
        np.minValue = this.resources.getInteger(R.integer.min_rate)
        np.wrapSelectorWheel = false
        AlertDialog.Builder(this)
            .setView(dialogDataBinding.root)
            .setTitle(getString(R.string.number_picker_title))
            .setPositiveButton(R.string.submit,
                { _, _ -> /* Value is handled via 2way Databinding */ }
            )
            .create()
            .show()
    }

}