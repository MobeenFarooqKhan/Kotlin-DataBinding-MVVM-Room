package com.example.testapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapp.data.db.entities.Comment

@Dao
interface CommentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(posts : List<Comment>)

    @Query("select * from Comments ORDER BY id")
    fun getAllComments():  LiveData<List<Comment>>

    @Query("DELETE FROM Comments")
    suspend fun deleteAllComments()

    @Query("select * from Comments where postId = :postId")
    suspend fun getCommentsSpecificToPost(postId : Int) : List<Comment>

}