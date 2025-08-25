package com.example.moviesapp.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.local.MoviesEntity
import com.example.moviesapp.data.repository.DataBaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalViewModel @Inject constructor(private val repo: DataBaseRepo):ViewModel() {

    fun insertMovies(result: MoviesEntity)=viewModelScope.launch(Dispatchers.IO) {
        repo.insertMovies(result)
    }
    val allMovies: LiveData<List<MoviesEntity>> = repo.getMovies()

}