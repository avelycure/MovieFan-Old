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

class PopularMovieAdapter(
    val onClickedItem: (PopularMovie) -> Unit
) :
    PagingDataAdapter<PopularMovie, PopularMovieAdapter.MovieViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(item = getItem(position), onClicked = onClickedItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_movie_item, parent, false)
        )
    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_title)
        val movieLogo = view.findViewById<AppCompatImageView>(R.id.pm_item_iv)
        val tvReviews = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_reviews)
        val ratingBar = view.findViewById<AppCompatRatingBar>(R.id.pm_item_rating_bar)
        val tvGenres = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_genres)
        val tvOriginalTitle =
            view.findViewById<AppCompatTextView>(R.id.pm_item_movie_original_title)

        fun bind(item: PopularMovie?, onClicked: (PopularMovie) -> Unit) {
            item?.let { popularMovie ->
                tvTitle.text = popularMovie.title
                tvReviews.text = popularMovie.voteCount.toString()
                ratingBar.rating = popularMovie.voteAverage / 2F
                tvOriginalTitle.text =
                    "${popularMovie.originalTitle}, ${popularMovie.releaseDate.substring(0, 4)}"
                tvGenres.text = buildString {
                    for (genreId in popularMovie.genreIds)
                        append(Constants.movieGenre[genreId] + " ")
                }
                movieLogo.load(Constants.IMAGE + popularMovie.posterPath) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                }
            }

            itemView.setOnClickListener {
                onClicked(item!!)
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