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

    fun postList(): LiveData<List<Post>?>? {
        return postList
    }
    fun getPosts(isInternetAvailable : Boolean) {
        Coroutines.io {
            val posts = repository.getPosts(isInternetAvailable)
            postList.postValue(posts)
        }
    }
}
