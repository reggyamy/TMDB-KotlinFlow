package com.keyta.moviedatabase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1 , exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
//    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "MoviesDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
    }
}