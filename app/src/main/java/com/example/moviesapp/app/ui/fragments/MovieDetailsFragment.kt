package com.example.moviesapp.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesapp.app.viewmodel.LocalViewModel
import com.example.moviesapp.data.local.MoviesEntity
import com.example.moviesapp.databinding.MovieDetailsfragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private lateinit var binding: MovieDetailsfragmentBinding
    private val localViewModel: LocalViewModel by viewModels()
    private var id: Int? = 0
    private val args:MovieDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=MovieDetailsfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        initButton()
        getAllMoviesFromLocalDatabase()
    }
    private fun bindViews(){
        binding.data=args.movies
    }

    private fun initButton(){
        binding.fab.setOnClickListener {
            insertMovies()
        }
        binding.ivFav.setOnClickListener {
            deleteFavouriteMovies()
        }
    }

    private fun insertMovies(){
        id = id?.plus(1)
        val movieEntity = MoviesEntity(
            id,
            args.movies?.id ?: 0,
            args.movies?.original_language,
            args.movies?.title,
            args.movies?.overview,
            args.movies!!.popularity,
            args.movies?.poster_path,
            args.movies?.release_date,
            args.movies?.title,
            args.movies!!.video,
            args.movies!!.vote_average,
            args.movies!!.vote_count
        )
        localViewModel.insertMovies(movieEntity)
        Snackbar.make(requireView(),"data saved Successfully",Snackbar.LENGTH_SHORT).show()
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigateUp()
        }

    }
    private fun getAllMoviesFromLocalDatabase() {
        localViewModel.allMovies.observe(viewLifecycleOwner) { movies ->
            val movie = movies.find { it.movieId == args.movies?.id }
            if (movie != null) {
                id = movie.id
                binding.ivFav.visibility = View.VISIBLE
                binding.fab.visibility = View.GONE
            } else {
                binding.ivFav.visibility = View.GONE
                binding.fab.visibility = View.VISIBLE
            }
        }

    }

    private fun deleteFavouriteMovies(){
        val movieEntity = MoviesEntity(
            id,
            args.movies?.id ?: 0,
            args.movies?.original_language,
            args.movies?.title,
            args.movies?.overview,
            args.movies!!.popularity,
            args.movies?.poster_path,
            args.movies?.release_date,
            args.movies?.title,
            args.movies!!.video,
            args.movies!!.vote_average,
            args.movies!!.vote_count
        )
        localViewModel.deleteMovies(movieEntity)
        Snackbar.make(requireView(),"movie deleted Successfully",Snackbar.LENGTH_SHORT).show()
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigateUp()
        }
    }

}