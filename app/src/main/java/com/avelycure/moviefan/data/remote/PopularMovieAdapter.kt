package com.avelycure.moviefan.data.remote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.domain.PopularMovie

class PopularMovieAdapter :
    PagingDataAdapter<PopularMovie, PopularMovieAdapter.MovieViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_movie_item, parent, false)
        )
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTitle = view.findViewById<TextView>(R.id.pm_item_movie_title)
        val tvOverview = view.findViewById<TextView>(R.id.pm_item_movie_overview)

        fun bind(item: PopularMovie?){
            item?.let {
                tvTitle.text = it.title
                tvOverview.text = it.overview
            }
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<PopularMovie>() {
        override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
            return oldItem == newItem
        }
    }
}