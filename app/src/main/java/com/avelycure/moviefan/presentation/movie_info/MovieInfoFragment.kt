package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_info, container, false)

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