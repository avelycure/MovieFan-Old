package com.avelycure.moviefan.presentation.person.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.avelycure.moviefan.R

/**
 * Adapter for handling situation when user scrolls faster than we get responses from server
 */
class PersonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PersonLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_view_persons, parent, false)
        )
    }

    class LoadStateViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val progress = view.findViewById<ProgressBar>(R.id.fp_load_state_progress)
        private val btnRetry = view.findViewById<Button>(R.id.fp_load_state_retry)
        private val tvErrorMessage = view.findViewById<AppCompatTextView>(R.id.fp_load_state_error_message)

        fun bind(loadState: LoadState, retry: () -> Unit) {

            btnRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            progress.isVisible = loadState is LoadState.Loading

            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }

            btnRetry.setOnClickListener {
                retry()
            }
        }
    }
}