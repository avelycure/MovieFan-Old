package com.avelycure.moviefan.presentation.person

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.ErrorCodes
import com.avelycure.moviefan.common.TemporaryConstants
import com.avelycure.moviefan.presentation.home.adapters.MovieLoadStateAdapter
import com.avelycure.moviefan.presentation.person.adapters.PersonAdapter
import com.avelycure.moviefan.utils.extensions.getQueryChangeStateFlow
import com.avelycure.moviefan.utils.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private val personViewModel: PersonViewModel by viewModels()
    private lateinit var personNameEditText: EditText
    private lateinit var rvPersons: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    @Inject
    lateinit var personAdapter: PersonAdapter
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_persons, container, false)
        initViewElements(view)
        return view
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchPopularPersons()
        lifecycleScope.launch {
            personViewModel
                .searchPerson(personNameEditText.getQueryChangeStateFlow())
                .flowOn(Dispatchers.Main)
                .collectLatest {
                    personAdapter.submitData(it)
                }
        }
    }

    private fun initViewElements(view: View) {
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.fp_toolbar))
        (activity as AppCompatActivity).supportActionBar?.title =
            TemporaryConstants.PERSON_SEARCH_TITLE_DEFAULT

        rvPersons = view.findViewById(R.id.fp_rv)
        personNameEditText = view.findViewById(R.id.fp_edit_text)
        loadingProgressBar = view.findViewById(R.id.fp_pb)

        swipeRefresh = view.findViewById(R.id.person_swipe_refresh)

        swipeRefresh.setOnRefreshListener {
            fetchPopularPersons()
            personNameEditText.setText("")
            swipeRefresh.isRefreshing = false
        }

        initRecyclerView()
    }

    private fun fetchPopularPersons(){
        lifecycleScope.launchWhenStarted {
            personViewModel
                .getPopularPersons()
                .collectLatest {
                    personAdapter.submitData(it)
                }
        }
    }

    private fun initRecyclerView() {
        rvPersons.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        personAdapter.scope = lifecycleScope
        personAdapter.onExpand = personViewModel::onExpand

        personAdapter.addLoadStateListener { loadState ->
            if (loadState.mediator?.refresh is LoadState.Loading)
                loadingProgressBar.visibility = View.VISIBLE
            else
                loadingProgressBar.visibility = View.GONE

            val errorState = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }

            errorState?.let {
                showError(
                    rvPersons,
                    requireContext(),
                    ErrorCodes.ERROR_NO_INTERNET_CONNECTION
                )
            }
        }
        rvPersons.adapter = personAdapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter { personAdapter.retry() }
        )
    }
}