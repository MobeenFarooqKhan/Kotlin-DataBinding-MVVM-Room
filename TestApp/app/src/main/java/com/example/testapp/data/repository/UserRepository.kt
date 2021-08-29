package com.example.testapp.data.repository


import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.user.User
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest

class UserRepository(private val myApi: MyApi,
                      private val myDatabase: AppDatabase
): SafeApiRequest() {
    private suspend fun getLatestUsersFromApi() : List<User> {
        return apiRequest { myApi.getUsers() }
    }
    private suspend fun getSpecificUsers(id : Int) : User = myDatabase.getUserDao().getSpecificUser(id)
    private suspend fun deleteAllUsers() = myDatabase.getUserDao().deleteAllUsers()
    private suspend fun saveAllUsers(users : List<User>) = myDatabase.getUserDao().upsert(users)

    suspend fun getUser(isInternetAvailable : Boolean, userId : Int) : User? {
        return if (isInternetAvailable) {
            val users = getLatestUsersFromApi()
            deleteAllUsers()
            saveAllUsers(users)
            users.find {  it.id == userId }
        }else {
            getSpecificUsers(userId)
        }
    }

}