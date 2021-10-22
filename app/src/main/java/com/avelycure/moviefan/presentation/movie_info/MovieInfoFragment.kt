package com.avelycure.moviefan.presentation.movie_info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.avelycure.moviefan.R

class MovieInfoFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.youtube_container, YTFragment())
        transaction.commit()

        val item: String = arguments?.getString("movie") ?: "no film"
        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
        return view
    }
}