package com.avelycure.moviefan.presentation.person

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R
import com.avelycure.moviefan.common.Constants
import com.avelycure.moviefan.data.remote.adapters.PersonAdapter
import com.avelycure.moviefan.presentation.home.MovieLoadStateAdapter
import com.avelycure.moviefan.utils.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class PersonFragment : Fragment() {

    private val personViewModel: PersonViewModel by viewModels()
    private lateinit var et: AppCompatEditText
    private lateinit var rvPersons: RecyclerView

    @Inject
    lateinit var personAdapter: PersonAdapter
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_person, container, false)
        Log.d("mytag", "step 1")
        initViewElements(view)
        Log.d("mytag", "step 2")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("mytag", "step 3")
        lifecycleScope.launchWhenStarted {
            personViewModel
                .searchPerson("Rose+Byrne")
                .collectLatest {
                    personAdapter.submitData(it)
                }
        }
    }

    private fun initViewElements(view: View) {
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).setSupportActionBar(view.findViewById(R.id.fp_toolbar))
        (activity as AppCompatActivity).supportActionBar?.title =
            Constants.PERSON_SEARCH_TITLE_DEFAULT

        rvPersons = view.findViewById(R.id.fp_rv)
        et = view.findViewById(R.id.fp_edit_text)
        loadingProgressBar = view.findViewById(R.id.fp_pb)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        rvPersons.layoutManager = LinearLayoutManager(requireContext())

        personAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW

        personAdapter.addLoadStateListener { loadState ->
            if (loadState.mediator?.refresh is LoadState.Loading)
                loadingProgressBar.visibility = View.VISIBLE
            else
                loadingProgressBar.visibility = View.GONE

            /*if (loadState.mediator?.refresh is LoadState.Error && personAdapter.itemCount == 0)
                btnRetry.visibility = View.VISIBLE
            else
                btnRetry.visibility = View.GONE*/

            val errorState = when {
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }

            errorState?.let {
                showError(
                    et,
                    requireContext(),
                    Constants.NO_INTERNET_CONNECTION
                )
            }
        }
        rvPersons.adapter = personAdapter.withLoadStateFooter(
            footer = MovieLoadStateAdapter { personAdapter.retry() }
        )
    }

    private fun showTips() {

    }
}