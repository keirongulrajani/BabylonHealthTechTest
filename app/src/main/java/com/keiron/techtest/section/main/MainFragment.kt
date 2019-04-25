package com.keiron.techtest.section.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.keiron.techtest.R
import com.keiron.techtest.di.ApplicationComponentHolder

class MainFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationComponentHolder.get().inject(this)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment(R.layout.fragment_main)
        }
    }
}