package com.example.news.services

import com.example.news.models.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/v2/top-headlines")
    fun list(@Query("q") query: String): Call<ArticleResult>
}