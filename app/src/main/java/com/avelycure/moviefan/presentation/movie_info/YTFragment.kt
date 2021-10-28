package com.avelycure.moviefan.presentation.movie_info

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.os.bundleOf
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.*

//todo check if user has actual version of youtube service https://developers.google.com/youtube/android/player/reference/com/google/android/youtube/player/YouTubeInitializationResult?hl=pt-br
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
        if (videoPath != "-1") {
            youTubePlayer?.cueVideo(videoPath);
            youTubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        } else {
            youTubePlayer?.release()
            this.parentFragment?.view?.let {
                val sb = Snackbar.make(
                    requireContext(),
                    it,
                    "No trailer available for this movie",
                    Snackbar.LENGTH_SHORT
                )
                (sb.view as Snackbar.SnackbarLayout).findViewById<TextView>(R.id.snackbar_text)
                    .setTextColor(Color.WHITE)
                (sb.view as Snackbar.SnackbarLayout).setBackgroundColor(resources.getColor(R.color.alazar_red))
                sb.show()
            }
        }
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