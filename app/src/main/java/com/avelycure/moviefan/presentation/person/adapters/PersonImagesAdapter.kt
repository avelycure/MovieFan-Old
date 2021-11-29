package com.avelycure.moviefan.presentation.person.adapters

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
import com.avelycure.moviefan.data.remote.dto.person.Profile

class PersonImagesAdapter(
    var personImages: List<Profile>,
    private val imageLoader: ImageLoader,
    private val context: Context
) : RecyclerView.Adapter<PersonImagesAdapter.PersonImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonImagesViewHolder {
        return PersonImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_image_in_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonImagesViewHolder, position: Int) {
        imageLoader.enqueue(
            ImageRequest.Builder(context)
                .data(Constants.IMAGE + personImages[position].file_path)
                .target(holder.image)
                .build()
        )
    }

    override fun getItemCount() = personImages.size

    class PersonImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.person_image_in_item_image)
    }
}