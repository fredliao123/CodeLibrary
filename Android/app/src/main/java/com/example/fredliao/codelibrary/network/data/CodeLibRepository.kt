package com.example.fredliao.codelibrary.network.data

import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import com.example.fredliao.codelibrary.network.CodeLibApiManager
import com.example.fredliao.codelibrary.spinner.SpinnerItem
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class CodeLibRepository(private val codeLibApiManager: CodeLibApiManager) {
    fun getUser(id: String): Observable<User> {
        return codeLibApiManager.getUser(id)
            .subscribeOn(Schedulers.io())
    }

    fun getSpinnerItem(): Observable<List<SpinnerItem>> {
        //Do your network or whatever here to get you spinner list
        val spinnerItem = arrayListOf<SpinnerItem>(
            SpinnerItem("item1"),
            SpinnerItem("item2"),
            SpinnerItem("item3"))
        return Observable.just(spinnerItem)
    }

    fun sendMagicLink(email: String, isQuickLogin: Boolean): Observable<MagicResponse> {
        return codeLibApiManager.sendMagicLink(email, isQuickLogin)
    }
}