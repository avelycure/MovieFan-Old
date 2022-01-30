package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.RequestConstants
import com.avelycure.moviefan.common.ConstantsUi
import com.avelycure.moviefan.common.ErrorCodes
import com.avelycure.moviefan.common.TemporaryConstants
import com.avelycure.moviefan.domain.models.Movie
import com.avelycure.moviefan.domain.models.MovieInfo
import com.avelycure.moviefan.domain.models.getters.*
import com.avelycure.moviefan.domain.state.ProgressBarState
import com.avelycure.moviefan.domain.state.UIComponent
import com.avelycure.moviefan.presentation.movie_info.adapters.MovieImagesAdapter
import com.avelycure.moviefan.presentation.movie_info.adapters.SimilarMoviesAdapter
import com.avelycure.moviefan.utils.ui.loadImage
import com.avelycure.moviefan.utils.ui.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * Fragment which shows detail information about movies
 */
@AndroidEntryPoint
class MovieInfoFragment : Fragment() {

    lateinit var movieImagesAdapter: MovieImagesAdapter
    lateinit var similarMoviesAdapter: SimilarMoviesAdapter

    private val movieInfoViewModel: MovieInfoViewModel by viewModels()
    private var movieId = ErrorCodes.ERROR_NO_TRAILER_CODE

    private lateinit var pb: ProgressBar
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
    private lateinit var rvMovieImages: RecyclerView
    private lateinit var rvSimilarMovies: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_info_fragment, container, false)
        movieId = arguments?.getInt(ConstantsUi.FRAGMENT_PARAMETER_MOVIE_ID)
            ?: ErrorCodes.ERROR_NO_TRAILER_CODE

        if (savedInstanceState == null)
            movieInfoViewModel.onTrigger(MovieInfoEvents.OnOpenInfoFragment(movieId = movieId))

        initViewElements(view)

        initRecyclerView()

        lifecycleScope.launchWhenStarted {
            movieInfoViewModel
                .state
                .collect { state ->
                    if (state.detailsLoadingState == ProgressBarState.Loading)
                        pb.visibility = View.VISIBLE
                    else {
                        pb.visibility = View.GONE
                        setUi(state.movieInfo, state.images, state.similar)
                    }

                    if (state.videoIsAvailable)
                        childFragmentManager
                            .beginTransaction()
                            .add(
                                R.id.youtube_container,
                                YTFragment.getInstance(state.videoInfo.key)
                            )
                            .commit()

                    if (!state.errorQueue.isEmpty()) {
                        val uiComponent = state.errorQueue.peek()
                        if (uiComponent is UIComponent.Dialog) {
                            showError(view, requireContext(), uiComponent.description)
                            movieInfoViewModel.onTrigger(MovieInfoEvents.OnRemoveHeadFromQueue)
                        }
                    }
                }
        }
        return view
    }

    private fun setUi(movieInfo: MovieInfo, images: List<String>, similar: List<Movie>) {
        loadImage(
            RequestConstants.IMAGE + movieInfo.posterPath,
            ivPoster
        )

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
        tvBudget.text =
            "${((movieInfo.budget.toFloat() / 1000000F).toInt()).toString()} million USD"
        tvRevenueTitle.text = "Revenue"
        tvRevenue.text =
            "${((movieInfo.revenue.toFloat() / 1000000F).toInt()).toString()} million USD"
        tvOverview.text = movieInfo.overview
        tvCastTitle.text = "Cast: "
        tvCast.text = movieInfo.getCast()
        movieImagesAdapter.imagesList = images
        similarMoviesAdapter.similarMovies = similar
    }

    private fun initViewElements(view: View) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.mi_toolbar))
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title =
            arguments?.getString(ConstantsUi.FRAGMENT_PARAMETER_MOVIE_TITLE)
                ?: TemporaryConstants.MOVIE_INFO_TITLE_DEFAULT

        pb = view.findViewById(R.id.mi_pb)
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
        rvMovieImages = view.findViewById(R.id.mi_rv_images)
        rvSimilarMovies = view.findViewById(R.id.mi_rv_similar_movies)
    }

    private fun initRecyclerView() {
        movieImagesAdapter = MovieImagesAdapter(emptyList())
        rvMovieImages.adapter = movieImagesAdapter
        rvMovieImages.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        similarMoviesAdapter = SimilarMoviesAdapter(emptyList())
        rvSimilarMovies.adapter = similarMoviesAdapter
        rvSimilarMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            activity?.supportFragmentManager?.popBackStack()
            true
        } else
            super.onOptionsItemSelected(item)
    }
}