package com.example.testapp.ui.activities.postsActivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.db.entities.Post
import com.example.testapp.data.db.entities.user.User
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.utils.Coroutines
import com.example.testapp.utils.LogData
import com.example.testapp.utils.isOnline
import kotlinx.coroutines.CoroutineScope

class PostsViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private var postList = MutableLiveData<List<Post>>()
    fun getCommentsSpecificToPost(): LiveData<List<Post>?>? {
        return postList
    }
    private fun getPostsApiCall(){
        Coroutines.io {
            val posts = repository.getLatestPostsFromApi()
            deletePosts()
            savePosts(posts)
            postList.postValue(posts)
        }
    }
    private suspend fun savePosts(posts : List<Post>) = repository.savePost(posts)
    private suspend fun deletePosts() = repository.deletePosts()
    fun getPosts(context: Context) {
        if (isOnline(context)){
            getPostsApiCall()
        }else {
            getPostsLocally()
        }
    }
    private fun getPostsLocally(){
        Coroutines.io {
            postList.postValue(repository.getAllPosts())
        }
    }
}
