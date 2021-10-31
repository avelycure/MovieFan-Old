package com.avelycure.moviefan.presentation.search_film

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants

class SearchFilmFragment: Fragment() {

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
        if((activity as AppCompatActivity).supportActionBar?.title == Constants.POPULAR_MOVIE_TITLE_DEFAULT)
            inflater.inflate(R.menu.toolbar_menu, menu)

        if((activity as AppCompatActivity).supportActionBar?.title == Constants.SEARCH_MOVIE_TITLE_DEFAULT)
            inflater.inflate(R.menu.search_film_menu, menu)
    }
}