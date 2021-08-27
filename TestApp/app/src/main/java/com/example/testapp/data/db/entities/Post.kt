package com.example.testapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Posts")
data class Posts(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo( defaultValue = "")  @SerializedName("id") val body: String,
    @ColumnInfo( defaultValue = "0")  @SerializedName("id") val id: Int,
    @ColumnInfo( defaultValue = "")  @SerializedName("id") val title: String,
    @ColumnInfo( defaultValue = "0")  @SerializedName("id")val userId: Int
)