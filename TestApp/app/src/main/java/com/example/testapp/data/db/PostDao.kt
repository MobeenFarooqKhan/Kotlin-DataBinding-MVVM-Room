package com.example.testapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.db.entities.Post

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(posts : List<Post>)

    @Query("select * from Posts ORDER BY id")
    suspend fun getAllPosts():  List<Post>

    @Query("DELETE FROM Posts")
    suspend fun deleteAllPosts()

}