package com.avelycure.moviefan.presentation.person

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class PersonFragment: Fragment() {

    private val personViewModel: PersonViewModel by viewModels()
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_person, container, false)
        initViewElements(view)
        return view
    }

    private fun initViewElements(view: View) {
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.fp_toolbar))
        (activity as AppCompatActivity).supportActionBar?.title =
            Constants.PERSON_SEARCH_TITLE_DEFAULT
    }

    /**
     * Creating menu and make searchView a stateFlow, so when typing we are getting movies that
     * matches the string we have already typed
     */
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if ((activity as AppCompatActivity).supportActionBar?.title == Constants.POPULAR_MOVIE_TITLE_DEFAULT) {
            menu.clear()
            inflater.inflate(R.menu.fp_toolbar_menu, menu)
            initSearchView(menu)
            showTips()

            /*lifecycleScope.launch {
                searchView.getQueryChangeStateFlow()
                    .debounce(500)
                    .filter { query ->
                        return@filter query.isNotEmpty()
                    }
                    .distinctUntilChanged()
                    .flatMapLatest { query ->
                        personViewModel.searchPerson(query)
                    }
                    .flowOn(Dispatchers.IO)
                    .collectLatest {
                        //personViewModel.submitData(it)
                    }
            }*/
        }
    }

    private fun showTips() {

    }

    private fun initSearchView(menu: Menu) {
        val searchManager =
            (activity as AppCompatActivity).getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.fp_search_view).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo((activity as AppCompatActivity).componentName))
        searchView.setIconifiedByDefault(false)

        // The default state of the homeFragment is showing popular movies, so when we close
        // searchView we are calling fetchPopularMovies()
        (menu.findItem(R.id.fp_search_view) as MenuItem).setOnActionExpandListener(object :
            MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                (activity as AppCompatActivity).invalidateOptionsMenu()
                return true
            }
        })
    }
}