package com.example.testapp.data.repository


import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.db.entities.Post
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest

class PostRepository (private val myApi: MyApi,
                      private val myDatabase: AppDatabase
): SafeApiRequest() {
    suspend fun getLatestPostsFromApi() : List<Post> {
        return apiRequest { myApi.getPosts() }
    }
    fun getAllPosts() : List<Post> = myDatabase.getPostDao().getAllPosts()
    suspend fun deletePosts() = myDatabase.getPostDao().deleteAllPosts()
    suspend fun savePost(post : List<Post>) = myDatabase.getPostDao().upsert(post)
}