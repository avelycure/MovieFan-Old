package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieInfoFragment : Fragment() {
    private val movieInfoViewModel: MovieInfoViewModel by viewModels()
    private var movieId = Constants.DEFAULT_VIDEO_ID

    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvOverview: AppCompatTextView
    private lateinit var tvTagline: AppCompatTextView
    private lateinit var tvReviews: AppCompatTextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var tvGenres: AppCompatTextView
    private lateinit var tvCountries: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        movieId = arguments?.getInt(Constants.ID_KEY) ?: Constants.DEFAULT_VIDEO_ID
        initViewElements(view)
        lifecycleScope.launch {
            movieInfoViewModel
                .getDetails(movieId)
                .collectLatest {
                    tvTitle.text = it.title
                    tvOverview.text = it.overview
                    tvTagline.text = it.tagline
                    tvReviews.text = it.voteCount.toString()
                    ratingBar.rating = it.voteAverage / 2F
                    tvGenres.text = buildString {
                        append("Genres: ")
                        for (element in it.genres)
                            append(Constants.movieGenre[element.id] + " ")
                    }
                    tvCountries.text = buildString {
                        append("Countries: ")
                        for (element in it.productionCountries)
                            append(element.name + " ")
                    }
                }
        }
        return view
    }

    private fun initViewElements(view: View) {
        tvTitle = view.findViewById(R.id.mi_title)
        tvOverview = view.findViewById(R.id.mi_overview)
        tvTagline = view.findViewById(R.id.mi_tagline)
        tvReviews = view.findViewById(R.id.mi_reviews)
        tvGenres = view.findViewById(R.id.mi_genres)
        ratingBar = view.findViewById(R.id.mi_ratingbar)
        tvCountries = view.findViewById(R.id.mi_countries)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            movieInfoViewModel
                .getVideos(movieId)
                .collectLatest {
                    val transaction = childFragmentManager.beginTransaction()
                    transaction.add(
                        R.id.youtube_container,
                        YTFragment.getInstance(it.key)
                    )
                    transaction.commit()
                }
        }
    }
}