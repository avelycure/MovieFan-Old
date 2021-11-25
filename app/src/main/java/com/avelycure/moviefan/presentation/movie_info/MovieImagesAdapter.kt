package com.avelycure.moviefan.presentation.movie_info

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants

class MovieImagesAdapter(
    var imagesList: List<String>,
    private val imageLoader: ImageLoader,
    private val context: Context
) : RecyclerView.Adapter<MovieImagesAdapter.MovieImagesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImagesViewHolder {
        return MovieImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieImagesViewHolder, position: Int) {
        imageLoader.enqueue(
            ImageRequest.Builder(context)
                .data(Constants.IMAGE + imagesList[position])
                .target(holder.image)
                .build()
        )
    }

    override fun getItemCount() = imagesList.size

    class MovieImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.movie_item_image)
    }
}