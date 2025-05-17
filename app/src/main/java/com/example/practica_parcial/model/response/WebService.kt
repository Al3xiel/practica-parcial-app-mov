package com.example.practica_parcial.model.response

import com.example.practica_parcial.model.beans.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WebService {
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id")id:Int): Response<Post>
}