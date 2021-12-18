package com.inc.vr.corp.app.kulbon.api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
    var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    var httpClient = OkHttpClient.Builder().addInterceptor(logging)

    private val retrofit = Retrofit.Builder()
        //.baseUrl("http://kulbon.my.id/api/") // change this IP for testing by your actual machine IP
        .baseUrl("http://192.168.43.235:8000/api/")
            //.baseUrl("https://kulbon.tech/public/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun <T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}