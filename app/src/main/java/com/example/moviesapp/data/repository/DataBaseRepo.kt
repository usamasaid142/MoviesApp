package com.example.moviesapp.data.repository



import com.example.moviesapp.data.local.MoviesDao
import com.example.moviesapp.data.local.MoviesEntity
import javax.inject.Inject

class DataBaseRepo @Inject constructor(private val dao: MoviesDao) {
    suspend fun insertMovies(result: MoviesEntity)=dao.insertMovies(result)
    fun getMovies()=dao.getMovies()
}
