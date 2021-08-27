package com.example.testapp.ui.activities.postsActivity

import androidx.lifecycle.ViewModel
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.utils.Coroutines
import com.example.testapp.utils.LogData
import kotlinx.coroutines.CoroutineScope

class PostsViewModel(
    private val repository: PostRepository
) : ViewModel() {

     fun getPostsApiCall(){
        Coroutines.io {
            val posts = repository.getLatestPostsFromApi()
            repository.deletePosts()
            repository.savePost(posts)
        }
    }
    fun getPosts() =  repository.getAllPosts()
}
