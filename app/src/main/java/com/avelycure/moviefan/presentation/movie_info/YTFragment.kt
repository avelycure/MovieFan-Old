package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.util.Log
import android.view.View
import com.avelycure.moviefan.common.Constants
import com.google.android.youtube.player.*

class YTFragment: YouTubePlayerFragment() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var onInitializedListener: YouTubePlayer.OnInitializedListener
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        onInitializedListener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                b: Boolean
            ) {
                youTubePlayer!!.loadVideo("W4hTJybfU7s")
                Log.d("mytag", "success init")
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider?,
                youTubeInitializationResult: YouTubeInitializationResult?
            ) {
                Log.d("mytag", "failed init")
            }
        }

        youTubePlayerView.initialize(Constants.YOUTUBE_API_KEY, onInitializedListener)
    }
}