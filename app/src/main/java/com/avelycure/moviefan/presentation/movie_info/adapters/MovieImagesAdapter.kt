package com.avelycure.moviefan.presentation.movie_info.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.utils.ui.loadImage

/**
 * This adapter manage recycler view with images of the movie
 */
class MovieImagesAdapter(
    var imagesList: List<String>
) : RecyclerView.Adapter<MovieImagesAdapter.MovieImagesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImagesViewHolder {
        return MovieImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_info_rv_images_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieImagesViewHolder, position: Int) {
        loadImage(
            RequestConstants.IMAGE + imagesList[position],
            holder.image
        )
    }

    override fun getItemCount() = imagesList.size

    class MovieImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.movie_item_image)
    }
}