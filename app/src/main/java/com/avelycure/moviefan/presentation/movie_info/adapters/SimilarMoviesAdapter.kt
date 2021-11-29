package com.avelycure.moviefan.presentation.movie_info.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.domain.models.Movie

class SimilarMoviesAdapter(
    var similarMovies: List<Movie>,
    private val imageLoader: ImageLoader,
    private val context: Context
) : RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarMoviesViewHolder {
        return SimilarMoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_similar_movie, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SimilarMoviesViewHolder, position: Int) {
        imageLoader.enqueue(
            ImageRequest.Builder(context)
                .data(RequestConstants.IMAGE + similarMovies[position].posterPath)
                .target(holder.image)
                .build()
        )
    }

    override fun getItemCount() = similarMovies.size

    class SimilarMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.similar_movie_image_item)
    }
}