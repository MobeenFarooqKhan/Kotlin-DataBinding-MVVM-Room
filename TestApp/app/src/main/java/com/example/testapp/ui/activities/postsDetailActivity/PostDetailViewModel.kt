package com.example.testapp.ui.activities.postsDetailActivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.db.entities.user.User
import com.example.testapp.data.repository.CommentsRepository
import com.example.testapp.data.repository.UserRepository
import com.example.testapp.utils.Coroutines
import com.example.testapp.utils.isOnline

class PostDetailViewModel(
    private val repository: UserRepository, private val commentsRepository: CommentsRepository
) : ViewModel() {

    private var selectedUser = MutableLiveData<User>()
    private var comments = MutableLiveData<List<Comment>>()

    fun getSelectedUser(): LiveData<User?>? {
        return selectedUser
    }
    fun getCommentsSpecificToPost(): LiveData<List<Comment>?>? {
        return comments
    }

    fun getUser(isInternetAvailable : Boolean,userId : Int){
        Coroutines.io {
            selectedUser.postValue(repository.getUser(isInternetAvailable,userId))
        }
    }
    fun getComments(isInternetAvailable : Boolean,postId : Int) {
        Coroutines.io {
            comments?.postValue(commentsRepository.getComments(isInternetAvailable,postId))
        }
    }

}
