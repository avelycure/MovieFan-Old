package com.avelycure.moviefan.presentation.search_film

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants

class SearchMovieFragment: Fragment() {
    private val searchMovieViewModel: SearchMovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search_film, container, false)
        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.pm_toolbar))
        (activity as AppCompatActivity).supportActionBar?.title = Constants.SEARCH_MOVIE_TITLE_DEFAULT
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if((activity as AppCompatActivity).supportActionBar?.title == Constants.SEARCH_MOVIE_TITLE_DEFAULT){
            menu.clear()
            inflater.inflate(R.menu.search_film_menu, menu)
        }
    }
}