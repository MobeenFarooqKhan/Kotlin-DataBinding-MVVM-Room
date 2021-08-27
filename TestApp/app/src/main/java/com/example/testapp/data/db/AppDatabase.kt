package com.example.testapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapp.data.db.entities.Posts

@Database(
        entities = [Posts::class],
        version = 2,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {


    abstract fun getPostDao() : PostDao
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
                "MyDatabase.db"
        ).fallbackToDestructiveMigration().build()

    }

}