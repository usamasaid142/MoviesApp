package com.example.moviesapp.app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.data.model.GetMoviesResponse
import com.example.moviesapp.data.repository.MoviesRepository
import com.example.moviesapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MoviesRepository):ViewModel() {

    val allMoviesResponse= MutableLiveData<Resource<GetMoviesResponse>>()
    val internetConnection = MutableLiveData<String?>()


    fun getAllMovies()=viewModelScope.launch(Dispatchers.IO+handler) {
        allMoviesResponse.postValue(Resource.Loading())
        val response=repo.getAllMovies()
        allMoviesResponse.postValue(response.let { handleGetAllProducts(it) })
    }

    private fun handleGetAllProducts(response: Response<GetMoviesResponse>): Resource<GetMoviesResponse> {
        return if (response.isSuccessful) {
            response.body()?.let {
                Resource.sucess(it)
            } ?: Resource.Error("Empty body")
        } else {
            Resource.Error(response.message())
        }
    }


    fun getError(error: String?){
        internetConnection.postValue(error)
    }

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, "exception occurred")
        getError(exception.message ?: "Unknown error")
    }
}