package com.avelycure.moviefan.data.remote.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.domain.mappers.setProperties
import com.avelycure.moviefan.domain.models.Person
import com.avelycure.moviefan.domain.models.PersonInfo
import com.avelycure.moviefan.domain.models.getMovies
import com.avelycure.moviefan.domain.models.getTvs
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonAdapter
@Inject constructor(
    val imageLoader: ImageLoader,
    @ApplicationContext val context: Context
) :
    PagingDataAdapter<Person, PersonAdapter.PersonViewHolder>(PersonComparator) {
    lateinit var scope: LifecycleCoroutineScope
    lateinit var onExpand: (Int) -> Flow<PersonInfo>

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(item = getItem(position), onExpand = onExpand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.person_item, parent, false)
        )
    }

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: AppCompatTextView = view.findViewById(R.id.pi_tv_name)
        private val ivPoster: AppCompatImageView = view.findViewById(R.id.person_item_iv)
        private val tvBiography: AppCompatTextView = view.findViewById(R.id.pi_biography)
        private val expLayout: ConstraintLayout = view.findViewById(R.id.pi_expandable_layout)
        private val layout: ConstraintLayout = view.findViewById(R.id.pi_layout)
        private val tvs: AppCompatTextView = view.findViewById(R.id.pi_tv_tv)
        private val movies: AppCompatTextView = view.findViewById(R.id.pi_tv_movie)

        fun bind(item: Person?, onExpand: (Int) -> Flow<PersonInfo>) {
            item?.let { person ->
                tvName.text = person.name
                tvBiography.text = person.biography
                tvs.text = person.getTvs()
                movies.text = person.getMovies()

                if (person.expanded)
                    expLayout.visibility = View.VISIBLE
                else
                    expLayout.visibility = View.GONE

                layout.setOnClickListener {

                    scope.launch {
                        onExpand(person.id)
                            .collect { personInfo ->
                                person.setProperties(personInfo)
                                notifyItemChanged(bindingAdapterPosition)
                            }
                    }

                    person.expanded = !person.expanded
                    notifyItemChanged(bindingAdapterPosition)
                }

                imageLoader.enqueue(
                    ImageRequest.Builder(context)
                        .data(Constants.IMAGE + person.profilePath)
                        .target(ivPoster)
                        .build()
                )
            }
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