package com.example.moviesapp.app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesapp.app.adapter.AllMoviesAdapter
import com.example.moviesapp.app.viewmodel.MoviesViewModel
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.databinding.MovieslistfragmentBinding
import com.example.moviesapp.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment(), AllMoviesAdapter.IMoviesListener {

  private lateinit var binding: MovieslistfragmentBinding
    private val movieViewModel: MoviesViewModel by viewModels()

    private val moviesAdapter : AllMoviesAdapter by lazy {
        AllMoviesAdapter(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= MovieslistfragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         productRecylerview()
        getProductCallBack()
    }

    private fun productRecylerview() {
        binding.rvMovies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
        }
    }

    private fun getProductCallBack() {
        movieViewModel.allMoviesResponse.observe(
            viewLifecycleOwner
        ) { response ->
            when (response) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.sucess -> {
                    binding.progressBar.visibility = View.GONE
                    response.let {
                        moviesAdapter.submitList(it.data?.results)
                    }
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(requireView(), "${response.message}", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

        movieViewModel.getAllMovies()
    }


    override fun onItemClicked(result: Movies) {
//        val action=MoviesListFragmentDirections.actionMoviesListFragmentToMovieDetailsFragment(result)
//        findNavController().navigate(action)
    }
}