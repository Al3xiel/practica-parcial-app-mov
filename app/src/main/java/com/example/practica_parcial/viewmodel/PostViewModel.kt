package com.example.practica_parcial.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.practica_parcial.model.beans.Post
import com.example.practica_parcial.model.client.RetrofitClient
import com.example.practica_parcial.model.db.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//View model works as an intermediary between the UI(views) and the data layer (model)

class PostViewModel: ViewModel(){

    //RETROFIT
    var post: Post = Post(0,0,"","")

    suspend fun getPost(id: Int){
        val response = RetrofitClient.webService.getPostById(id)
        if (response.body()!= null) {
            post = response.body()!!
        }
    }

    //ROOM
    var postList: MutableList<Post> by mutableStateOf(mutableListOf())

    fun getPostList(context: Context){
        var appDB: AppDataBase = AppDataBase.getDatabase(context)
        GlobalScope.launch(Dispatchers.IO) {
            postList = appDB.postDao().postList().toMutableList()
        }
    }

    fun insertPost(context: Context, post: Post){
        var appDB: AppDataBase = AppDataBase.getDatabase(context)
        GlobalScope.launch(Dispatchers.IO) {
            appDB.postDao().insertPost(post)
        }
    }
}