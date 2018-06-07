package com.example.fredliao.codelibrary.login

import android.databinding.Bindable
import com.example.fredliao.codelibrary.common.BaseViewModel
import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import com.example.fredliao.codelibrary.network.data.CodeLibRepository
import com.example.fredliao.codelibrary.BR
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val codeLibRepository: CodeLibRepository) : BaseViewModel() {

    private var user = User()
    private var magicResponse = MagicResponse()

    val id = "1.json"

    val name: String
        @Bindable
        get() = magicResponse.token ?: ""

    val email: String
        @Bindable
        get() = ""

    fun login() {
        codeLibRepository.sendMagicLink("fred@ittybittyapps.com", false)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::magicResponse)
    }

    private fun magicResponse(magicResponse: MagicResponse) {
        this.magicResponse = magicResponse
        notifyPropertyChanged(BR.name)
    }

    private fun updateUser(user: User) {
        this.user = user
    }

}