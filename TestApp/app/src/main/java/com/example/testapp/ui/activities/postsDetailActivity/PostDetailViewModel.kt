package com.example.testapp.ui.activities.postsDetailActivity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testapp.data.db.entities.user.User
import com.example.testapp.data.repository.PostRepository
import com.example.testapp.data.repository.UserRepository
import com.example.testapp.utils.Coroutines
import com.example.testapp.utils.isOnline

class PostDetailViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private var selectedUser = MutableLiveData<User>()
    fun getSelectedUser(): LiveData<User?>? {
        return selectedUser
    }
    private fun getUsersApiCall(id : Int){
        Coroutines.io {
            val users = repository.getLatestUsersFromApi()
            repository.deleteAllUsers()
            repository.saveAllUsers(users)
            getSpecificUser(id)
        }
    }
    fun getUser(context: Context,userId : Int){
        if (isOnline(context)){
            getUsersApiCall(userId)
        }else {
            getSpecificUser(userId)
        }
    }
    private fun getSpecificUser(id : Int) {
          Coroutines.io {
              selectedUser?.postValue(repository.getSpecificUsers(id))
          }
    }
}
