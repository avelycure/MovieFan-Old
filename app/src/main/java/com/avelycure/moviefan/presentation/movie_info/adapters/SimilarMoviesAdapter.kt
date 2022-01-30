package com.avelycure.moviefan.presentation.movie_info.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.domain.models.Movie
import com.avelycure.moviefan.utils.ui.loadImage

/**
 * This adapter handles recycler view with posters of similar movies
 */
class SimilarMoviesAdapter(
    var similarMovies: List<Movie>
) : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        return SimilarMoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_info_rv_similar_movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        loadImage(
            RequestConstants.IMAGE + similarMovies[position].posterPath,
            holder.image
        )
    }

    override fun getItemCount() = similarMovies.size

    class SimilarMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.similar_movie_image_item)
    }
}