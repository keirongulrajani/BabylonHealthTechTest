package com.keiron.techtest.section.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.keiron.techtest.R
import com.keiron.techtest.di.ApplicationComponentHolder
import com.keiron.techtest.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationComponentHolder.get().inject(this)

        this.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        viewModel.onMainPageCreated()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment(R.layout.fragment_main)
        }
    }
}