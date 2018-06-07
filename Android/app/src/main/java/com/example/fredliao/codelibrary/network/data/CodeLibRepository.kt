package com.example.fredliao.codelibrary.network.data

import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import com.example.fredliao.codelibrary.network.CodeLibApiManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CodeLibRepository(private val codeLibApiManager: CodeLibApiManager) {
    fun getUser(id : String): Observable<User> {
        return codeLibApiManager.getUser(id)
            .subscribeOn(Schedulers.io())
    }

    fun sendMagicLink(email: String, isQuickLogin: Boolean): Observable<MagicResponse> {
        return codeLibApiManager.sendMagicLink(email, isQuickLogin)
    }
}