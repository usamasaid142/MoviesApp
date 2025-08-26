package com.example.moviesapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(result: MoviesEntity)
    @Delete
    suspend fun deleteMovies(result: MoviesEntity)
    @Query("DELETE FROM movie_table WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int?)
    @Query(" select * from movie_table")
    fun getMovies(): LiveData<List<MoviesEntity>>

}