package com.keiron.techtest.section.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.keiron.techtest.R

class DetailsFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {
    companion object {
        private const val ARG_SELECTED_POST_ID = "detailsFragment_selectedPost"

        fun newInstance(id: Int): DetailsFragment {
            val args: Bundle = Bundle().apply { putInt(ARG_SELECTED_POST_ID, id) }
            return DetailsFragment(R.layout.fragment_details).apply { arguments = args }
        }
    }
}