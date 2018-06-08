package com.example.fredliao.codelibrary.network

import android.content.Context
import com.example.fredliao.codelibrary.R
import com.example.fredliao.codelibrary.model.MagicRequest
import com.example.fredliao.codelibrary.model.MagicResponse
import com.example.fredliao.codelibrary.model.User
import io.reactivex.Observable
import io.reactivex.functions.Function
import moe.banana.jsonapi2.JsonApiConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

class CodeLibApiManager(context: Context) {
    private val serviceApi: CodeLibApi
    private val errorInterceptor = Interceptor { chain ->
        val request = chain.request()
        val response = chain.proceed(request)
        // if(response.code() == 422){
        // Do something, when there is a error
        // return@Interceptor response
        // }
        response
    }

    init {
        val client = OkHttpClient.Builder()
            // To show request and response body in logcat
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(errorInterceptor)
            .addNetworkInterceptor(this::withCommonRequestHeaders)
            // Use anonymous function
            .addInterceptor(fun(chain: Interceptor.Chain): Response { return chain.proceed(chain.request()) })
            .build()

        val retrofitJsonApi = Retrofit.Builder()
            .baseUrl(context.getString(R.string.api_base_url))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JsonApiConverterFactory.create(JsonApiDataSingleton.moshi))
            .client(client)
            .build()

        serviceApi = retrofitJsonApi.create(CodeLibApi::class.java)
    }

    fun getUser(id: String): Observable<User> {
        return serviceApi.getUser(id)
            .retryWhen(RetryWithCertainMessage("test", 1, 1000))
    }

    fun sendMagicLink(email: String, isQuickLogin: Boolean): Observable<MagicResponse> =
        serviceApi.loginMagicToken(MagicRequest(email, isQuickLogin))

    private fun withCommonRequestHeaders(chain: Interceptor.Chain): Response =
        chain.proceed(chain.request())
            .newBuilder()
            // Add headers here
            // .header("Content-Type", "application/vnd.api+json")
            // .header("API-Version", "4")
            // .header("User-Agent", "Supp/Android")
            // .header("Accept-Language", "en-us")
            // .header("Connection", "keep-alive")
            .build()

    /**
     * Based on http://blog.makingiants.com/retry-on-timeout/
     *
     * Retry observable subscription if it emits a certain Throwable with a certain message
     *
     * For every retry it will wait delay + delayAmount so we wait more and more every retry.
     *
     * This is used in retryWhen
     * retryWhen's definition is like this :
     * public final Observable<T> retryWhen(final Function<? super Observable<Throwable>, ? extends ObservableSource<?>> handler)
     * So it returns a Observable<T> and takes a Function type parameter named handler
     * handler has two wildcards. first is something that is super class of Observable<Throwable>. And the first parameter of
     * Function is the input parameter. And because Producer Extends, Consumer Super. This handler will need to consume
     * a Observable<Throwable> type variable. And produce a Observable that can emit any type.
     * @param maxRetries number of retries
     * @param delay milliseconds of wait between each try
     * @param delayAmount delay + delayAmount
     */
    class RetryWithCertainMessage(val message: String, private val maxRetries: Int, private var delay: Long, private val delayAmount: Long = 100)
        : Function<Observable<Throwable>, Observable<*>> {
        internal var retryCount = 0

        override fun apply(t: Observable<Throwable>): Observable<*> {
            return t.flatMap({
                if (++retryCount < maxRetries && it.message.equals(message)) {
                    delay += delayAmount
                    Observable.timer(delay, TimeUnit.MILLISECONDS)
                } else {
                    Observable.error(it)
                }
            })
        }
    }

    // Function can be replaced by this. Note that `override operator fun invoke`
    class RetryWithCertainMessageAnotherWrite(val message: String, private val maxRetries: Int, private var delay: Long, private val delayAmount: Long = 100)
        : (Observable<Throwable>) -> Observable<*> {
        internal var retryCount = 0

        override operator fun invoke(t: Observable<Throwable>): Observable<*> {
            return t.flatMap({
                if (++retryCount < maxRetries && it.message.equals(message)) {
                    delay += delayAmount
                    Observable.timer(delay, TimeUnit.MILLISECONDS)
                } else {
                    Observable.error(it)
                }
            })
        }
    }

    val functionType = RetryWithCertainMessageAnotherWrite("whatever", 1, 1)
    // If I want to invoke this function just
    fun invokeFunctionType() {
        functionType.invoke(Observable.error(Throwable()))
        // Or just
        functionType(Observable.error(Throwable()))
    }
}