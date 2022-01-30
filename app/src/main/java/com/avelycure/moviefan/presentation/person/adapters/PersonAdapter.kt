package com.avelycure.moviefan.presentation.person.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.domain.mappers.setProperties
import com.avelycure.moviefan.domain.models.*
import com.avelycure.moviefan.domain.models.getters.*
import com.avelycure.moviefan.utils.ui.loadImage
import com.avelycure.moviefan.utils.ui.showIfNotBlank
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This adapter handles recycler view on PersonFragment
 */
@Singleton
class PersonAdapter
@Inject constructor(
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
                .inflate(R.layout.persons_rv_item, parent, false)
        )
    }

    inner class PersonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: AppCompatTextView = view.findViewById(R.id.pi_tv_name)
        private val ivPoster: AppCompatImageView = view.findViewById(R.id.person_item_iv)
        private val tvBiography: AppCompatTextView = view.findViewById(R.id.pi_biography)
        private val expLayout: ConstraintLayout = view.findViewById(R.id.pi_expandable_layout)
        private val layout: ConstraintLayout = view.findViewById(R.id.pi_layout)
        private val tvs: AppCompatTextView = view.findViewById(R.id.pi_tv_tv)
        private val tvsTitle: AppCompatTextView = view.findViewById(R.id.pi_tv_tv_title)
        private val movies: AppCompatTextView = view.findViewById(R.id.pi_tv_movie)
        private val moviesTitle: AppCompatTextView = view.findViewById(R.id.pi_tv_movie_title)
        private val tvDeathday: AppCompatTextView = view.findViewById(R.id.person_deathday)
        private val tvDeathdayTitle: AppCompatTextView =
            view.findViewById(R.id.person_deathday_title)
        private val tvAlsoKnownAs: AppCompatTextView =
            view.findViewById(R.id.person_item_also_known_as)
        private val tvAlsoKnownAsTitle: AppCompatTextView =
            view.findViewById(R.id.person_item_also_known_as_title)
        private val tvBiographyTitle: AppCompatTextView = view.findViewById(R.id.pi_biography_title)

        private val tvHomepage: AppCompatTextView = view.findViewById(R.id.person_homepage)
        private val tvHomepageTitle: AppCompatTextView =
            view.findViewById(R.id.person_homepage_title)
        private val tvPlaceOfBirth: AppCompatTextView =
            view.findViewById(R.id.person_place_of_birth)
        private val tvPlaceOfBirthTitle: AppCompatTextView =
            view.findViewById(R.id.person_place_of_birth_title)
        private val tvDateOfBirth: AppCompatTextView = view.findViewById(R.id.person_birthday)
        private val tvDateOfBirthTitle: AppCompatTextView =
            view.findViewById(R.id.person_birthday_title)

        private val rvPersonImages: RecyclerView = view.findViewById(R.id.person_item_rv)
        private val tvDepartment: AppCompatTextView = view.findViewById(R.id.pi_tv_department)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: Person?, onExpand: (Int) -> Flow<PersonInfo>) {
            item?.let { person ->
                tvName.text = person.name
                showIfNotBlank(tvs, tvsTitle, person.getTvs())
                showIfNotBlank(movies, moviesTitle, person.getMovies())
                tvDepartment.text = person.knownForDepartment

                showIfNotBlank(tvDateOfBirth, tvDateOfBirthTitle, person.birthday)
                showIfNotBlank(tvPlaceOfBirth, tvPlaceOfBirthTitle, person.placeOfBirth)
                showIfNotBlank(tvDeathday, tvDeathdayTitle, person.deathDay)
                showIfNotBlank(tvAlsoKnownAs, tvAlsoKnownAsTitle, person.getAlsoKnownAs())
                showIfNotBlank(tvBiography, tvBiographyTitle, person.biography)
                showIfNotBlank(tvHomepage, tvHomepageTitle, person.homepage)

                if (person.expanded)
                    expLayout.visibility = View.VISIBLE
                else
                    expLayout.visibility = View.GONE

                tvHomepage.setOnClickListener {
                    if (person.homepage?.isNotBlank() == true) {
                        val uri = Uri.parse(person.homepage)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(context, intent, null)
                    }
                }

                layout.setOnClickListener {
                    val personImagesAdapter = PersonImagesAdapter(emptyList())
                    rvPersonImages.adapter = personImagesAdapter
                    rvPersonImages.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    person.expanded = !person.expanded

                    scope.launch {
                        onExpand(person.id)
                            .collect { personInfo ->
                                val inputChanged = person.setProperties(personInfo)
                                if (inputChanged) {
                                    bind(person, onExpand)
                                    if (person.profileImages.isNotEmpty()) {
                                        personImagesAdapter.personImages = person.profileImages
                                        personImagesAdapter.notifyDataSetChanged()
                                    }
                                }
                            }
                    }
                    bind(person, onExpand)
                }

                loadImage(
                    RequestConstants.IMAGE + person.profilePath,
                    ivPoster
                )
            }
        }
    }

    object PersonComparator : DiffUtil.ItemCallback<Person>() {
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }
    }
}