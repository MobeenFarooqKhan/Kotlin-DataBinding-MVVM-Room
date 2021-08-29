package com.example.testapp.data.repository


import android.content.Context
import com.example.testapp.data.db.AppDatabase
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.network.MyApi
import com.example.testapp.data.network.SafeApiRequest
import com.example.testapp.utils.isOnline

class CommentsRepository(private val myApi: MyApi,
                         private val myDatabase: AppDatabase
): SafeApiRequest() {
    private suspend fun getLatestCommentsFromApi() : List<Comment> {
        return apiRequest { myApi.getcomments() }
    }

    private suspend fun getAllComments() = myDatabase.getCommentsDao().getAllComments()
    private suspend fun getCommentsSpecificToPost(postId : Int) : List<Comment> =
        myDatabase.getCommentsDao().getCommentsSpecificToPost(postId)

    private suspend fun deleteAllComments() = myDatabase.getCommentsDao().deleteAllComments()

    private suspend fun saveAllComments(comments : List<Comment>) = myDatabase.getCommentsDao().upsert(comments)

    public suspend fun getComments(isInternetAvailable : Boolean, postId : Int) : List<Comment> {
        return if (isInternetAvailable){
            val commentsFromApi = getLatestCommentsFromApi()
            deleteAllComments()
            saveAllComments(commentsFromApi)
            commentsFromApi.filter {  it.postId == postId }
        }else {
            getCommentsSpecificToPost(postId)
        }
    }
}