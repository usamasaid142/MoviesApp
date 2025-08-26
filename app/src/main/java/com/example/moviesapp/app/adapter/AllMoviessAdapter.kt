package com.example.moviesapp.app.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.data.model.MovieId
import com.example.moviesapp.data.model.Movies
import com.example.moviesapp.databinding.ItemLayoutMoviesBinding


class AllMoviesAdapter (val listener:IMoviesListener,val moviesList:List<MovieId>) :
    ListAdapter<Movies, AllMoviesAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemLayoutMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = getItem(position)

        holder.binding.apply {
            data = result
        }
        holder.binding.layoutCard.setOnClickListener {
            listener.onItemClicked(result)
        }
        holder.binding.fab.setOnClickListener {
            listener.addToFavorite(result)
        }
        if (moviesList.any { it.id == result.id }) {
            holder.binding.ivFav.visibility = View.VISIBLE
          //  holder.binding.fab.visibility = View.GONE
        } else {
            holder.binding.ivFav.visibility = View.GONE
           // holder.binding.fab.visibility = View.VISIBLE
        }
    }
    class ViewHolder(itemBinding: ItemLayoutMoviesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        internal val binding: ItemLayoutMoviesBinding = itemBinding
    }
    private class DiffCallback : DiffUtil.ItemCallback<Movies>() {
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return true
        }
    }


    interface IMoviesListener{
        fun onItemClicked(result:Movies)
        fun addToFavorite(result: Movies)
    }

}