package com.keiron.techtest.section.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.keiron.techtest.R
import com.keiron.techtest.drawable.LoadingDrawable
import com.keiron.techtest.section.main.model.MainUiModel
import kotlinx.android.synthetic.main.listitem_post.view.*

class PostSnippetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.title
    private val author = itemView.author
    private val authorAvatar = itemView.authorAvatar
    private val numberOfComments = itemView.numberOfComments

    fun onBind(
        itemUiModel: MainUiModel,
        onPostClickedListener: OnPostClickedListener
    ) {
        title.text = itemUiModel.postTitle
        author.text = itemUiModel.authorTitle
        numberOfComments.text = itemView.resources.getQuantityString(
            R.plurals.comments,
            itemUiModel.numberOfComments,
            itemUiModel.numberOfComments
        )

        Glide.with(itemView)
            .load(itemUiModel.authorAvatar.imageUrl)
            .placeholder(LoadingDrawable(itemView.context))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(authorAvatar)

        itemView.setOnClickListener {
            onPostClickedListener.onPostClicked(itemUiModel.id)
        }
    }
}