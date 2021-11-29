package com.avelycure.moviefan.presentation.movie_info

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.avelycure.moviefan.common.ConstantsUi
import com.avelycure.moviefan.common.RequestConstants
import com.google.android.youtube.player.*

//todo check if user has actual version of youtube service https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubeInitializationResult?hl=pt-br
/**
 * Fragment to represent youtube video
 */
class YTFragment : YouTubePlayerSupportFragmentX(), YouTubePlayer.OnInitializedListener {
    private lateinit var videoPath: String

    companion object {
        fun getInstance(videoPath: String) = YTFragment().apply {
            arguments = bundleOf(ConstantsUi.YT_FRAGMENT_PARAMETER_VIDEO_PATH to videoPath)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(ConstantsUi.YT_FRAGMENT_PARAMETER_VIDEO_PATH)?.let {
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
    ) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initialize(RequestConstants.YOUTUBE_API_KEY, this)
    }
}