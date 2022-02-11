package com.avelycure.moviefan.presentation.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.common.TemporaryConstants
import com.avelycure.moviefan.domain.models.Movie
import com.avelycure.moviefan.domain.models.getters.getOriginalTitleAndReleaseDate
import com.avelycure.moviefan.utils.ui.loadImage

/**
 * Adapter of the recycler view in home fragment. It is used to show popular movies and
 * movies that were found when user began searching
 */
class MovieAdapter:
    PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieComparator) {
    var onClickedItem: (Movie) -> Unit = {}

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(item = getItem(position), onClicked = onClickedItem)
        holder.itemView.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.rv_animation
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.home_rv_item, parent, false)
        )
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle = view.findViewById<AppCompatTextView>(R.id.pmi_title)
        private val movieLogo = view.findViewById<AppCompatImageView>(R.id.pmi_iv)
        private val tvReviews = view.findViewById<AppCompatTextView>(R.id.pmi_tv_reviews)
        private val ratingBar = view.findViewById<AppCompatRatingBar>(R.id.pmi_rating_bar)
        private val tvGenres = view.findViewById<AppCompatTextView>(R.id.pmi_tv_genres)
        private val tvOriginalTitle =
            view.findViewById<AppCompatTextView>(R.id.pmi_original_title)

        fun bind(item: Movie?, onClicked: (Movie) -> Unit) {
            item?.let { popularMovie ->
                tvTitle.text = popularMovie.title
                tvReviews.text = popularMovie.voteCount.toString()
                ratingBar.rating = popularMovie.voteAverage / 2F
                tvOriginalTitle.text = popularMovie.getOriginalTitleAndReleaseDate()
                tvGenres.text = buildString {
                    for (genreId in popularMovie.genreIds)
                        append(TemporaryConstants.movieGenre[genreId] + " ")
                }

                loadImage(
                    RequestConstants.IMAGE + popularMovie.posterPath,
                    movieLogo
                )
            }

            itemView.setOnClickListener {
                onClicked(item!!)
            }
        }
    }

    object MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.movieId == newItem.movieId
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}