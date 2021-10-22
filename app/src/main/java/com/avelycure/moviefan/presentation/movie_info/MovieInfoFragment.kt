package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avelycure.moviefan.R
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.android.youtube.player.YouTubePlayerView

class MovieInfoFragment : YouTubePlayerFragment() {
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var onInitializedListener: YTInitializedListener

    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_movie_info, container, false)

        youTubePlayerView = view.findViewById(R.id.youtube_player_view)

        return view
    }
}