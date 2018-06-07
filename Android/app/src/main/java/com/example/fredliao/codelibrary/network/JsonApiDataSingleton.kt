package com.example.fredliao.codelibrary.network

import com.example.fredliao.codelibrary.model.MagicRequest
import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import com.squareup.moshi.Moshi
import moe.banana.jsonapi2.ResourceAdapterFactory

object JsonApiDataSingleton {
    private val jsonApoAdapterFactory = ResourceAdapterFactory.builder()
        .add(User::class.java)
        .add(MagicResponse::class.java)
        .add(MagicRequest::class.java)
        .build()

    val moshi = Moshi.Builder()
        .add(jsonApoAdapterFactory)
        .build()
}