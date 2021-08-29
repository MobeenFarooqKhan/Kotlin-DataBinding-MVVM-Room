package com.example.testapp.data.repository


import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.db.entities.Post
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest

class PostRepository (private val myApi: MyApi,
                      private val myDatabase: AppDatabase
): SafeApiRequest() {
    private suspend fun getLatestPostsFromApi() : List<Post> {
        return apiRequest { myApi.getPosts() }
    }
    private suspend fun getAllPosts() : List<Post> = myDatabase.getPostDao().getAllPosts()
    private suspend fun deletePosts() = myDatabase.getPostDao().deleteAllPosts()
    private suspend fun savePost(post : List<Post>) = myDatabase.getPostDao().upsert(post)

    suspend fun getPosts(isInternetAvailable : Boolean) :  List<Post> {
        return if (isInternetAvailable) {
            val posts = getLatestPostsFromApi()
            deletePosts()
            savePost(posts)
            posts
        }else {
            getAllPosts()
        }
    }
}