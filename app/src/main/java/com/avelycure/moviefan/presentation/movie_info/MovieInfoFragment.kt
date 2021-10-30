package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
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
import com.avelycure.moviefan.domain.models.*
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
    private lateinit var tvTagline: AppCompatTextView
    private lateinit var ratingBar: AppCompatRatingBar
    private lateinit var tvReviews: AppCompatTextView
    private lateinit var tvCastTitle: AppCompatTextView
    private lateinit var tvCast: AppCompatTextView
    private lateinit var tvGenresTitle: AppCompatTextView
    private lateinit var tvGenres: AppCompatTextView
    private lateinit var tvCountriesTitle: AppCompatTextView
    private lateinit var tvCountries: AppCompatTextView
    private lateinit var tvBudget: AppCompatTextView
    private lateinit var tvBudgetTitle: AppCompatTextView
    private lateinit var tvRevenue: AppCompatTextView
    private lateinit var tvRevenueTitle: AppCompatTextView
    private lateinit var tvCompaniesTitle: AppCompatTextView
    private lateinit var tvCompanies: AppCompatTextView
    private lateinit var ivPoster: AppCompatImageView
    private lateinit var tvOverview: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        movieId = arguments?.getInt(Constants.ID_KEY) ?: Constants.NO_TRAILER_CODE
        lifecycleScope.launch {
            movieInfoViewModel
                .getDetails(movieId)
                .collectLatest { movieInfo ->
                    setUi(movieInfo)
                }
        }
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
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.mi_toolbar))
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title =
            arguments?.getString(Constants.MOVIE_TITLE) ?: Constants.MOVIE_INFO_TITLE_DEFAULT
        initViewElements(view)
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
        tvGenresTitle.text = "Genres: "
        tvGenres.text = movieInfo.getGenres()
        tvCountriesTitle.text = "Countries: "
        tvCountries.text = movieInfo.getCountries()
        tvCompaniesTitle.text = "Companies: "
        tvCompanies.text = movieInfo.getCompanies()
        tvBudgetTitle.text = "Budget: "
        tvBudget.text = "${((movieInfo.budget.toFloat() / 1000000F).toInt()).toString()} million USD"
        tvRevenueTitle.text = "Revenue"
        tvRevenue.text = "${((movieInfo.revenue.toFloat() / 1000000F).toInt()).toString()} million USD"
        tvOverview.text = movieInfo.overview
        tvCastTitle.text = "Cast: "
        tvCast.text = movieInfo.getCast()
    }

    private fun initViewElements(view: View) {
        tvTitle = view.findViewById(R.id.mi_title)
        tvOverview = view.findViewById(R.id.mi_overview)
        tvTagline = view.findViewById(R.id.mi_tagline)
        tvReviews = view.findViewById(R.id.mi_reviews)
        tvGenresTitle = view.findViewById(R.id.mi_genres_title)
        tvGenres = view.findViewById(R.id.mi_genres)
        ratingBar = view.findViewById(R.id.mi_ratingbar)
        tvCountriesTitle = view.findViewById(R.id.mi_countries_title)
        tvCountries = view.findViewById(R.id.mi_countries)
        tvBudget = view.findViewById(R.id.mi_budget)
        tvBudgetTitle = view.findViewById(R.id.mi_budget_title)
        tvRevenueTitle = view.findViewById(R.id.mi_revenue_title)
        tvRevenue = view.findViewById(R.id.mi_revenue)
        tvCompaniesTitle = view.findViewById(R.id.mi_companies_title)
        tvCompanies = view.findViewById(R.id.mi_companies)
        ivPoster = view.findViewById(R.id.mi_poster)
        tvCast = view.findViewById(R.id.mi_cast)
        tvCastTitle = view.findViewById(R.id.mi_cast_title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            activity?.supportFragmentManager?.popBackStack()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}