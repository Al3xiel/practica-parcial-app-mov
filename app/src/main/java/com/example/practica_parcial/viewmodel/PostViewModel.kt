package com.example.practica_parcial.viewmodel

import androidx.lifecycle.ViewModel
import com.example.practica_parcial.model.beans.Post
import com.example.practica_parcial.model.client.RetrofitClient

//View model works as an intermediary between the UI(views) and the data layer (model)

class PostViewModel: ViewModel(){

    var post: Post = Post(1,1,"","")

    suspend fun getPost(id: Int){
        val response = RetrofitClient.webService.getPostById(id)
        if (response.body()!= null) {
            post = response.body()!!
        }
    }
}