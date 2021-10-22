package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView

class MovieInfoFragment : YouTubePlayerFragment() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_movie_info, container, false)

        youTubePlayerView = view.findViewById(R.id.youtube_player_view)

        onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {

            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                result: YouTubeInitializationResult?
            ) {

            }
        }

        youTubePlayerView.initialize(Constants.YOUTUBE_API_KEY, onInitializedListener)

        return view
    }
}