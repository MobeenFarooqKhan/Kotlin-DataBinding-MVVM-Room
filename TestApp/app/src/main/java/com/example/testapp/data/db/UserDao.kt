package com.example.testapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.db.entities.Post
import com.example.testapp.data.db.entities.user.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(posts : List<User>)

    @Query("select * from User ORDER BY id")
    fun getAllUsers():  LiveData<List<User>>

    @Query("DELETE FROM User")
    suspend fun deleteAllUsers()

    @Query("select * from User where id = :id")
    suspend fun getSpecificUser(id : Int) : User

}