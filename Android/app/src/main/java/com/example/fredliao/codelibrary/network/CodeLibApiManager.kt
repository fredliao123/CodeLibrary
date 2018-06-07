package com.example.fredliao.codelibrary.network

import android.content.Context
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.model.MagicRequest
import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import io.reactivex.Observable
import moe.banana.jsonapi2.JsonApiConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class CodeLibApiManager(context: Context) {
    private val serviceApi : CodeLibApi
    private val errorInterceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        //if(response.code() == 422){
        //Do something, when there is a error
        //return@Interceptor response
        //}
        response
    }

    init {
        val client = OkHttpClient.Builder()
            //To show request and response body in logcat
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(errorInterceptor)
            .addNetworkInterceptor(this::withCommonRequestHeaders)
            .build()

        val retrofitJsonApi = Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_base_url))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JsonApiConverterFactory.create(JsonApiDataSingleton.moshi))
            .client(client)
            .build()

        serviceApi = retrofitJsonApi.create(CodeLibApi::class.java)
    }

    fun getUser(id : String) : Observable<User> {
        return serviceApi.getUser(id)
    }

    fun sendMagicLink(email: String, isQuickLogin: Boolean): Observable<MagicResponse> =
        serviceApi.loginMagicToken(MagicRequest(email, isQuickLogin))

    private fun withCommonRequestHeaders(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request())
            .newBuilder()
            //Add headers here
            //.header("Content-Type", "application/vnd.api+json")
            //.header("API-Version", "4")
            //.header("User-Agent", "Supp/Android")
            //.header("Accept-Language", "en-us")
            //.header("Connection", "keep-alive")
            .build()
}