package com.avelycure.moviefan.presentation.movie_info

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.avelycure.moviefan.common.Constants
import com.google.android.youtube.player.*

//todo check if user has actual version of youtube service https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubeInitializationResult?hl=pt-br
/**
 * Fragment to represent youtube video
 */
class YTFragment : YouTubePlayerSupportFragmentX(), YouTubePlayer.OnInitializedListener {
    private lateinit var videoPath: String

    companion object {
        fun getInstance(videoPath: String) = YTFragment().apply {
            arguments = bundleOf(Constants.VIDEO_PATH_KEY to videoPath)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(Constants.VIDEO_PATH_KEY)?.let {
            videoPath = it
        }
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        b: Boolean
    ) {
        youTubePlayer?.cueVideo(videoPath);
        youTubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        Log.d("mytag", "" + youTubeInitializationResult?.name)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize(Constants.YOUTUBE_API_KEY, this)
    }
}