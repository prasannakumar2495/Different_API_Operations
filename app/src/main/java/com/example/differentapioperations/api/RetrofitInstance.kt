package com.example.differentapioperations.api

import com.example.differentapioperations.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//to make the class as singleton, we are using "object" keyword.
object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: SimpleAPI by lazy {
        retrofit.create(SimpleAPI::class.java)
    }

    //both the above 2 variables can also be declared using lazy function.
}