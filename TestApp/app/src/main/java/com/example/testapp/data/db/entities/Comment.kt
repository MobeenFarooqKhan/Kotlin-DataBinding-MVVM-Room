package com.example.testapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Comments")
data class Comment(
    @SerializedName("postId") val postId : Int,
    @PrimaryKey  @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("email") val email : String,
    @SerializedName("body") val body : String
)