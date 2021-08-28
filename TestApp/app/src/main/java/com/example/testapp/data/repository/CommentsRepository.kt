package com.example.testapp.data.repository


import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest

class CommentsRepository(private val myApi: MyApi,
                         private val myDatabase: AppDatabase
): SafeApiRequest() {
    suspend fun getLatestCommentsFromApi() : List<Comment> {
        return apiRequest { myApi.getcomments() }
    }

    fun getAllComments() = myDatabase.getCommentsDao().getAllComments()
    suspend fun getCommentsSpecificToPost(postId : Int) : List<Comment> =
        myDatabase.getCommentsDao().getCommentsSpecificToPost(postId)

    suspend fun deleteAllComments() = myDatabase.getCommentsDao().deleteAllComments()

    suspend fun saveAllComments(comments : List<Comment>) = myDatabase.getCommentsDao().upsert(comments)
}