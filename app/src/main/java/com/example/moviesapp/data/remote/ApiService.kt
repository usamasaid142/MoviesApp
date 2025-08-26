package com.example.moviesapp.data.remote

import com.example.moviesapp.data.model.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("3/discover/movie")
    suspend fun getAllMovies(
   @Query("primary_release_year") primary_Release_Year:String="2024",
    ):Response<GetMoviesResponse>

}