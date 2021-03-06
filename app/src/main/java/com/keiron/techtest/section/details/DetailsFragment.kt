package com.keiron.techtest.section.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.keiron.babylonhealth.ui.components.extensions.observe
import com.keiron.babylonhealth.ui.components.recyclerview.decoration.SimpleDividerItemDecoration
import com.keiron.techtest.R
import com.keiron.techtest.di.ApplicationComponentHolder
import com.keiron.techtest.drawable.LoadingDrawable
import com.keiron.techtest.section.details.model.DetailsViewState
import com.keiron.techtest.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_details.author
import kotlinx.android.synthetic.main.fragment_details.authorAvatar
import kotlinx.android.synthetic.main.fragment_details.body
import kotlinx.android.synthetic.main.fragment_details.commentList
import kotlinx.android.synthetic.main.fragment_details.contentContainer
import kotlinx.android.synthetic.main.fragment_details.errorText
import kotlinx.android.synthetic.main.fragment_details.progress
import kotlinx.android.synthetic.main.fragment_details.title
import javax.inject.Inject

class DetailsFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<DetailsViewModel>

    private lateinit var commentAdapter: CommentAdapter

    private lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationComponentHolder.get().inject(this)

        this.viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DetailsViewModel::class.java).apply {
                observe(detailsViewState, ::onDetailsViewStateChanged)
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentAdapter = CommentAdapter()
        commentList.apply {
            addItemDecoration(SimpleDividerItemDecoration(context))
            adapter = commentAdapter
        }
        val postId = arguments!!.getInt(ARG_SELECTED_POST_ID)
        viewModel.onDetailsPageCreatedForPost(postId)
    }

    private fun onDetailsViewStateChanged(detailsViewState: DetailsViewState?) {
        detailsViewState?.let {
            progress.visibility = if (detailsViewState.loading) View.VISIBLE else View.GONE
            errorText.visibility =
                if (detailsViewState.error !is DetailsViewState.Error.None) View.VISIBLE else View.GONE
            errorText.text = getErrorText(detailsViewState.error)

            updateDetailsViews(detailsViewState, it)
        }
    }

    private fun updateDetailsViews(
        detailsViewState: DetailsViewState,
        it: DetailsViewState
    ) {
        if (!detailsViewState.loading && detailsViewState.error is DetailsViewState.Error.None) {
            contentContainer.visibility = View.VISIBLE
            title.text = it.detailsUiModel!!.postTitle
            author.text = it.detailsUiModel.authorTitle
            body.text = it.detailsUiModel.body

            Glide.with(authorAvatar)
                .load(it.detailsUiModel.authorAvatar.imageUrl)
                .placeholder(LoadingDrawable(authorAvatar.context))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(authorAvatar)

            commentAdapter.updateUiModels(it.detailsUiModel.comments)
        } else contentContainer.visibility = View.GONE
    }

    private fun getErrorText(error: DetailsViewState.Error): String {
        return when (error) {
            DetailsViewState.Error.None -> ""
            is DetailsViewState.Error.NoPostFound -> getString(R.string.details_no_post_found, error.id)
            is DetailsViewState.Error.NetworkIssue -> error.title
        }
    }

    companion object {
        private const val ARG_SELECTED_POST_ID = "detailsFragment_selectedPost"

        fun newInstance(id: Int): DetailsFragment {
            val args: Bundle = Bundle().apply { putInt(ARG_SELECTED_POST_ID, id) }
            return DetailsFragment(R.layout.fragment_details).apply { arguments = args }
        }
    }
}