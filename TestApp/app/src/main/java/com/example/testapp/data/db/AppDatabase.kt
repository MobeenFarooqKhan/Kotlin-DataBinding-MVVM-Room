package com.example.testapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.data.db.converters.Converter
import com.example.testapp.data.db.entities.Comment
import com.example.testapp.data.db.entities.Post
import com.example.testapp.data.db.entities.user.User

@Database(
        entities = [Post::class,User::class, Comment::class],
        version = 2,
        exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun getPostDao() : PostDao
    abstract fun getUserDao() : UserDao
    abstract fun getCommentsDao() : CommentsDao
    companion object {
        @Volatile
        private var instanse : AppDatabase? = null
        private val LOCK = Any()

        operator  fun invoke(context : Context) = instanse ?: synchronized(LOCK) {
            instanse ?: buildDatabase(context).also {
                instanse = it
            }
        }

        private fun buildDatabase(context: Context) = Room.
        databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyTestDatabase.db"
        ).fallbackToDestructiveMigration().build()

    }

}