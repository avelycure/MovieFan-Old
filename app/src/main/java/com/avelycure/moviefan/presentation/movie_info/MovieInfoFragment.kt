package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.avelycure.moviefan.R

class MovieInfoFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.youtube_container, YTFragment())
        transaction.commit()
    }
}