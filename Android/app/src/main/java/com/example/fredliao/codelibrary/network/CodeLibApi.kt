package com.example.fredliao.codelibrary.network

import com.example.fredliao.codelibrary.model.MagicRequest
import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CodeLibApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: String) : Observable<User>

    @POST("auth/magic_tokens")
    fun loginMagicToken(@Body data: MagicRequest): Observable<MagicResponse>
}