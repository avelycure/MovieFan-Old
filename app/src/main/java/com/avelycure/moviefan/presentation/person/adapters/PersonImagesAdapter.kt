package com.avelycure.moviefan.presentation.person.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.data.remote.dto.person.Profile
import com.avelycure.moviefan.utils.ui.loadImage

/**
 * This adapter handles recyclerView with person images(when parent recyclerView expands)
 */
class PersonImagesAdapter(
    var personImages: List<Profile>
) : RecyclerView.Adapter<PersonImagesAdapter.PersonImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonImagesViewHolder {
        return PersonImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.persons_rv_person_image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonImagesViewHolder, position: Int) {
        loadImage(
            RequestConstants.IMAGE + personImages[position].file_path,
            holder.image
        )
    }

    override fun getItemCount() = personImages.size

    class PersonImagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: AppCompatImageView = itemView.findViewById(R.id.persons_iv)
    }
}