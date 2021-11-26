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
import com.avelycure.moviefan.domain.models.Person
import com.avelycure.moviefan.utils.extensions.getOriginalTitleAndReleaseDate
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonAdapter
@Inject constructor(
    val imageLoader: ImageLoader,
    @ApplicationContext val context: Context
) :
    PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(PersonComparator) {
    var onClickedItem: (Person) -> Unit = {}

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(item = getItem(position), onClicked = onClickedItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_item, parent, false)
        )
    }

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Person?, onClicked: (Person) -> Unit) {

        }
    }

    object PersonComparator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}