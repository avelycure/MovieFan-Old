package com.avelycure.moviefan.data.remote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
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
        val tvTitle = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_title)
        val tvOverview = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_overview)
        val movieLogo = view.findViewById<AppCompatImageView>(R.id.pm_item_iv)
        val tvReviews = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_reviews)
        val ratingBar = view.findViewById<AppCompatRatingBar>(R.id.pm_item_rating_bar)

        fun bind(item: PopularMovie?){
            item?.let {
                tvTitle.text = it.title
                tvOverview.text = it.overview
                tvReviews.text = it.popularity.toString()
                ratingBar.rating = (it.popularity.toInt()/2000F)
                movieLogo.load(Constants.IMAGE + it.posterPath){
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                }
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