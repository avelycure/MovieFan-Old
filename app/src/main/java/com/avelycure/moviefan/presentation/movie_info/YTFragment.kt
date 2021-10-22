package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.util.Log
import android.view.View
import com.avelycure.moviefan.common.Constants
import com.google.android.youtube.player.*

class YTFragment : YouTubePlayerSupportFragmentX(), YouTubePlayer.OnInitializedListener {
    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        b: Boolean
    ) {
        youTubePlayer?.cueVideo("W4hTJybfU7s");
        youTubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize(Constants.YOUTUBE_API_KEY, this)
    }
}