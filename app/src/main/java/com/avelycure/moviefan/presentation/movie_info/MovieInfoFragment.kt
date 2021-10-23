package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvOverview: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        initViewElements(view)

        return view
    }

    private fun initViewElements(view: View) {
        tvTitle = view.findViewById(R.id.mi_title)
        tvOverview = view.findViewById(R.id.mi_overview)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            movieInfoViewModel
                .getVideos(arguments?.getInt(Constants.ID_KEY) ?: Constants.DEFAULT_VIDEO_ID)
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