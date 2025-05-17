package com.example.practica_parcial.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practica_parcial.model.beans.Post

// Database Access Object (DAO) for the Post entity
// Dao is an interface that provides methods for accessing the database
@Dao
interface PostDAO {
    @Query("select * from posts")
    fun postList(): List<Post>

    @Insert
    fun insertPost(post: Post)
}