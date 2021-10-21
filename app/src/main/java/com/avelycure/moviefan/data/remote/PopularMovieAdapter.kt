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

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieGenre = mapOf(
            28 to "Action",
            12 to "Adventure",
            16 to "Animation",
            35 to "Comedy",
            80 to "Crime",
            99 to "Documentary",
            18 to "Drama",
            10751 to "Family",
            14 to "Fantasy",
            36 to "History",
            27 to "Horror",
            10402 to "Music",
            9648 to "Mystery",
            10749 to "Romance",
            878 to "Science Fiction",
            10770 to "TV Movie",
            53 to "Thriller",
            10752 to "War",
            37 to "Western"
        )

        val tvTitle = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_title)
        val tvOverview = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_overview)
        val movieLogo = view.findViewById<AppCompatImageView>(R.id.pm_item_iv)
        val tvReviews = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_reviews)
        val ratingBar = view.findViewById<AppCompatRatingBar>(R.id.pm_item_rating_bar)
        val tvGenres = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_genres)
        val tvOriginalTitle = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_original_title)

        fun bind(item: PopularMovie?) {
            item?.let {
                        popularMovie ->
                tvTitle.text = popularMovie.title
                tvOverview.text = popularMovie.overview
                tvReviews.text = popularMovie.popularity.toString()
                ratingBar.rating = popularMovie.voteAverage / 2F
                tvOriginalTitle.text = popularMovie.originalTitle
                tvGenres.text = buildString {
                    for(genreId in popularMovie.genreIds)
                        append(movieGenre[genreId] + " ")
                }
                movieLogo.load(Constants.IMAGE + popularMovie.posterPath) {
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