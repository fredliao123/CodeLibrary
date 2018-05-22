package com.example.fredliao.codelibrary.common

import android.arch.lifecycle.LifecycleRegistry
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.reactivex.disposables.CompositeDisposable

open class BaseActivity<ViewModelType : BaseViewModel> : AppCompatActivity() {
    private val lifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }
    protected lateinit var viewModel: ViewModelType

    val contentView: View?
        get() = findViewById(android.R.id.content)

    val disposable = CompositeDisposable()

    override fun getLifecycle(): LifecycleRegistry = lifecycleRegistry

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}