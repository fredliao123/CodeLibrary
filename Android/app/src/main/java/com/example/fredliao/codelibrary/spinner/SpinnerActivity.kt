package com.example.fredliao.codelibrary.spinner

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.common.BaseActivity
import com.example.fredliao.codelibrary.databinding.ActivitySpinnerBinding
import com.example.fredliao.codelibrary.router.ViewModelFactory
import me.tatarka.bindingcollectionadapter2.BR
import me.tatarka.bindingcollectionadapter2.ItemBinding

class SpinnerActivity : BaseActivity<SpinnerViewModel>() {
    val binding: ActivitySpinnerBinding by lazy {
        DataBindingUtil.setContentView<ActivitySpinnerBinding>(this, R.layout.activity_spinner)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = setupViewModel()
        binding.spinnerItemBinding = ItemBinding.of<SpinnerItem>(BR.itemViewModel, R.layout.spinner_item)
    }

    private fun setupViewModel() : SpinnerViewModel{
        val factory = ViewModelFactory(application, intent)
        viewModel = ViewModelProviders.of(this, factory).get(SpinnerViewModel::class.java)
        return viewModel
    }

}