package com.keiron.techtest.section.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.keiron.babylonhealth.ui.components.extensions.observe
import com.keiron.babylonhealth.ui.components.recyclerview.decoration.SimpleDividerItemDecoration
import com.keiron.techtest.R
import com.keiron.techtest.di.ApplicationComponentHolder
import com.keiron.techtest.section.details.DetailsFragment
import com.keiron.techtest.section.main.model.MainViewState
import com.keiron.techtest.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment(contentLayoutId: Int) : Fragment(contentLayoutId), OnPostClickedListener {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>

    private lateinit var postAdapter: PostSnippetAdapter

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationComponentHolder.get().inject(this)

        this.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java).apply {
                observe(mainViewState, ::onMainViewStateChanged)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        viewModel.onMainPageCreated()
    }

    private fun setupViews() {
        postAdapter = PostSnippetAdapter(this)
        postList.apply {
            setHasFixedSize(true)
            addItemDecoration(SimpleDividerItemDecoration(context))
            adapter = postAdapter
        }
    }

    private fun onMainViewStateChanged(mainViewState: MainViewState?) {
        mainViewState?.let {
            progress.visibility = if (mainViewState.loading) View.VISIBLE else View.GONE
            errorText.visibility = if (mainViewState.error !is MainViewState.Error.None) View.VISIBLE else View.GONE
            errorText.text = getErrorText(mainViewState.error)

            postAdapter.updateUiModels(it.mainUiModels)
        }
    }

    override fun onPostClicked(postId: Int) {
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.parent, DetailsFragment.newInstance(postId))
            .addToBackStack(null)
            .commit()
    }

    private fun getErrorText(error: MainViewState.Error): String {
        return when (error) {
            is MainViewState.Error.None -> ""
            is MainViewState.Error.NetworkIssue -> error.title
        }
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment(R.layout.fragment_main)
        }
    }
}