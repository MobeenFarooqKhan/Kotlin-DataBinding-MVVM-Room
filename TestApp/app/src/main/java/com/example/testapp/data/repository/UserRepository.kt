package com.example.testapp.data.repository


import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.user.User
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest

class UserRepository(private val myApi: MyApi,
                      private val myDatabase: AppDatabase
): SafeApiRequest() {
    suspend fun getLatestUsersFromApi() : List<User> {
        return apiRequest { myApi.getUsers() }
    }
    fun getAllUsers() = myDatabase.getUserDao().getAllUsers()
    suspend fun getSpecificUsers(id : Int) : User = myDatabase.getUserDao().getSpecificUser(id)
    suspend fun deleteAllUsers() = myDatabase.getUserDao().deleteAllUsers()
    suspend fun saveAllUsers(users : List<User>) = myDatabase.getUserDao().upsert(users)
}