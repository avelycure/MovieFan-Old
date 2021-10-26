package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.ImageLoader
import coil.request.ImageRequest
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.domain.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieInfoFragment : Fragment() {
    @Inject
    lateinit var imageLoader: ImageLoader

    private val movieInfoViewModel: MovieInfoViewModel by viewModels()
    private var movieId = Constants.NO_TRAILER_CODE

    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvOverview: AppCompatTextView
    private lateinit var tvTagline: AppCompatTextView
    private lateinit var tvReviews: AppCompatTextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var tvGenres: AppCompatTextView
    private lateinit var tvCountries: AppCompatTextView
    private lateinit var tvBudget: AppCompatTextView
    private lateinit var tvRevenue: AppCompatTextView
    private lateinit var tvCompanies: AppCompatTextView
    private lateinit var ivPoster: AppCompatImageView
    private lateinit var tvCast: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        movieId = arguments?.getInt(Constants.ID_KEY) ?: Constants.NO_TRAILER_CODE
        initViewElements(view)
        lifecycleScope.launch {
            movieInfoViewModel
                .getDetails(movieId)
                .collectLatest { movieInfo ->
                    setUi(movieInfo)
                }
        }
        return view
    }

    private fun setUi(movieInfo: MovieInfo) {
        imageLoader.enqueue(ImageRequest.Builder(requireContext())
            .data(Constants.IMAGE + movieInfo.posterPath)
            .target(ivPoster)
            .build())
        tvTitle.text = movieInfo.title
        tvTagline.text = movieInfo.tagline
        ratingBar.rating = movieInfo.voteAverage
        tvReviews.text = movieInfo.voteCount.toString()
        tvGenres.text = movieInfo.getGenres()
        tvCountries.text = movieInfo.getCountries()
        tvCompanies.text = movieInfo.getCompanies()
        tvBudget.text = movieInfo.budget.toString()
        tvRevenue.text = movieInfo.revenue.toString()
        tvOverview.text = movieInfo.overview
        tvCast.text = movieInfo.getCast()
    }

    private fun initViewElements(view: View) {
        tvTitle = view.findViewById(R.id.mi_title)
        tvOverview = view.findViewById(R.id.mi_overview)
        tvTagline = view.findViewById(R.id.mi_tagline)
        tvReviews = view.findViewById(R.id.mi_reviews)
        tvGenres = view.findViewById(R.id.mi_genres)
        ratingBar = view.findViewById(R.id.mi_ratingbar)
        tvCountries = view.findViewById(R.id.mi_countries)
        tvBudget = view.findViewById(R.id.mi_budget)
        tvRevenue = view.findViewById(R.id.mi_revenue)
        tvCompanies = view.findViewById(R.id.mi_companies)
        ivPoster = view.findViewById(R.id.mi_poster)
        tvCast = view.findViewById(R.id.mi_cast)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.toolbar))
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title =
            arguments?.getString(Constants.MOVIE_TITLE) ?: "Movie info"

        lifecycleScope.launch {
            movieInfoViewModel
                .getVideos(movieId)
                .collectLatest {
                    childFragmentManager
                        .beginTransaction()
                        .add(
                            R.id.youtube_container,
                            YTFragment.getInstance(it.key)
                        )
                        .commit()
                }
        }
    }
}