package com.example.fredliao.codelibrary.edittext

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.common.BaseActivity
import com.example.fredliao.codelibrary.databinding.ActivityPrefixSuffixEditTextBinding
import com.example.fredliao.codelibrary.router.ViewModelFactory
import kotlinx.android.synthetic.main.activity_prefix_suffix_edit_text.*

class PrefixSuffixEditTextActivity : BaseActivity<PrefixSuffixEditTextViewModel>() {
    val binding: ActivityPrefixSuffixEditTextBinding by lazy {
        DataBindingUtil.setContentView<ActivityPrefixSuffixEditTextBinding>(this, R.layout.activity_prefix_suffix_edit_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.model = setupViewModel()
        initEditText()
    }

    private fun setupViewModel(): PrefixSuffixEditTextViewModel {
        val factory = ViewModelFactory(application, intent)
        viewModel = ViewModelProviders.of(this, factory).get(PrefixSuffixEditTextViewModel::class.java)
        return viewModel
    }

    private fun initEditText() {
        minimumrate_edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                viewModel.rateEmpty = editable.toString().isBlank()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        minimumrate_edittext.setOnFocusChangeListener { _, b ->
            viewModel.minimumRateFocused = b
        }
    }


    fun minimumRateWrapperClicked(v: View) {
        //Request focus explicitly for minimumRate's Edittext
        minimumrate_edittext.requestFocus()
        //Move the cursor to the end of text
        minimumrate_edittext.setSelection(minimumrate_edittext.text.length)
        //Show soft keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(minimumrate_edittext, InputMethodManager.SHOW_IMPLICIT)
    }


    /**
     * way to hide soft key board
     */
    private fun hideSoftKeyboard() {
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //Any widget's window token will be fine
        inputMethodManager.hideSoftInputFromWindow(binding.minimumrateEdittext.windowToken, 0)
    }
}