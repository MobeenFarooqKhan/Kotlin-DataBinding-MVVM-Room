package com.example.testapp.data.db.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Posts")
data class Post(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("body") val body: String,
    @SerializedName("title") val title: String,
    @SerializedName("userId")val userId: Int
): Parcelable