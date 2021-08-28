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
    private fun getUserApiCall(id : Int){
        Coroutines.io {
            val users = repository.getLatestUsersFromApi()
            repository.deleteAllUsers()
            repository.saveAllUsers(users)
            getSpecificUser(id)
        }
    }
    private fun getCommentsApiCall(postId: Int){
        Coroutines.io {
            val comments = commentsRepository.getLatestCommentsFromApi()
            commentsRepository.deleteAllComments()
            commentsRepository.saveAllComments(comments)
            getCommentsSpecificToPost(postId)
        }
    }
    fun getUser(context: Context,userId : Int){
        if (isOnline(context)){
            getUserApiCall(userId)
        }else {
            getSpecificUser(userId)
        }
    }
    fun getComments(context: Context,postId : Int){
        if (isOnline(context)){
           getCommentsApiCall(postId)
        }else {
            getCommentsSpecificToPost(postId)
        }
    }

    private fun getSpecificUser(id : Int) {
          Coroutines.io {
              selectedUser?.postValue(repository.getSpecificUsers(id))
          }
    }
    private fun getCommentsSpecificToPost(postId : Int) {
        Coroutines.io {
            comments?.postValue(commentsRepository.getCommentsSpecificToPost(postId))
        }
    }
}
