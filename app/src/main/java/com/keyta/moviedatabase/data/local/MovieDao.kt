package com.keyta.moviedatabase.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Transaction
    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<MovieEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieEntity: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovieById(id: String): LiveData<MovieEntity>

}