package com.example.fredliao.codelibrary.login

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.common.BaseActivity
import com.example.fredliao.codelibrary.databinding.ActivityLoginBinding
import com.example.fredliao.codelibrary.router.ViewModelFactory

class LoginActivity : BaseActivity<LoginViewModel>() {

    val binding : ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = setupViewModel()
    }

    private fun setupViewModel() : LoginViewModel {
        val factory = ViewModelFactory(application, intent)
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        return viewModel
    }

}