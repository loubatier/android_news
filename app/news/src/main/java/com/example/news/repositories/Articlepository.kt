package com.example.news.repositories

import android.os.Build
import com.example.news.BuildConfig
import com.example.news.models.Article
import com.example.news.services.ArticleService
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class Articlepository {
    private val service: ArticleService

    fun list(query: String): List<Article> {
        val response = service.list(query).execute()
        return response.body() ?.articles ?: emptyList()
    }

    private val requestInterceptor: Interceptor = Interceptor { chain ->
        val original: Request = chain.request()

        val url: HttpUrl = original.url
            .newBuilder()
            .addQueryParameter("apiKey", "8ecd1476368944999647068fb59092c5")
            // .addQueryParameter("country", Locale.getDefault().language)
            .build()

        val device = Build.MANUFACTURER + "-" + Build.MODEL
        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
            .addHeader("User-Agent", "Android-${BuildConfig.VERSION_CODE}-($device)")
            .addHeader("Accept-Language", Locale.getDefault().language)
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .url(url)

        val request = requestBuilder.build()
        return@Interceptor chain.proceed(request)
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

    init {

        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org")
        }.addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(ArticleService::class.java)
    }

}