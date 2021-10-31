package com.avelycure.moviefan.data.remote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.domain.models.Movie
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ApplicationContext

class MovieAdapter
@AssistedInject constructor(
    @Assisted val onClickedItem: (Movie) -> Unit,
    val imageLoader: ImageLoader,
    @ApplicationContext val context: Context
) :
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieComparator) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(item = getItem(position), onClicked = onClickedItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_movie_item, parent, false)
        )
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.findViewById<AppCompatTextView>(R.id.pm_item_movie_title)
        private val movieLogo = view.findViewById<AppCompatImageView>(R.id.pm_item_iv)
        private val tvReviews = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_reviews)
        private val ratingBar = view.findViewById<AppCompatRatingBar>(R.id.pm_item_rating_bar)
        private val tvGenres = view.findViewById<AppCompatTextView>(R.id.pm_item_tv_genres)
        private val tvOriginalTitle =
            view.findViewById<AppCompatTextView>(R.id.pm_item_movie_original_title)

        fun bind(item: Movie?, onClicked: (Movie) -> Unit) {
            item?.let { popularMovie ->
                tvTitle.text = popularMovie.title
                tvReviews.text = popularMovie.voteCount.toString()
                ratingBar.rating = popularMovie.voteAverage / 2F
                tvOriginalTitle.text = popularMovie.getOriginalTitleAndReleaseDate()
                tvGenres.text = buildString {
                    for (genreId in popularMovie.genreIds)
                        append(Constants.movieGenre[genreId] + " ")
                }
                imageLoader.enqueue(
                    ImageRequest.Builder(context)
                        .data(Constants.IMAGE + popularMovie.posterPath)
                        .target(movieLogo)
                        .build()
                )
            }

            itemView.setOnClickListener {
                onClicked(item!!)
            }
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    fun Movie.getOriginalTitleAndReleaseDate(): String =
        if (this.releaseDate.isNotEmpty())
            originalTitle + this.releaseDate.substring(0, 4)
        else
            originalTitle
}