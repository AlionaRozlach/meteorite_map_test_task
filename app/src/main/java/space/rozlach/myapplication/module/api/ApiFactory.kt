package space.rozlach.myapplication.module.api

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val BASE_URL = "https://data.nasa.gov"



    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val request: Request =
            chain.request().newBuilder().addHeader("X-App-Token", "R9IJaGqLbAZcDkro3E5qIV4wz").build()
        chain.proceed(request)
    }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(httpClient.build())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(ApiService::class.java)
}