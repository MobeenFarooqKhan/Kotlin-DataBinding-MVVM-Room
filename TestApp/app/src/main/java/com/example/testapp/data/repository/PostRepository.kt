package com.example.testapp.data.repository


import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.Posts
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest

class PostRepository (private val myApi: MyApi,
                      private val myDatabase: AppDatabase
): SafeApiRequest() {
    suspend fun getPostsFromApi() : List<Posts> {
        return apiRequest { myApi.getPosts() }
    }
    suspend fun getAllPosts() = myDatabase.getPostDao().getAllPosts()
    suspend fun deletePosts() = myDatabase.getPostDao().deleteAllPosts()
    suspend fun savePost(post : Posts) = myDatabase.getPostDao().upsert(post)

}