package com.keiron.techtest.section.details

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.keiron.techtest.section.details.model.CommentUiModel
import kotlinx.android.synthetic.main.listitem_comment.view.*

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val author = itemView.author
    private val name = itemView.name
    private val authorAvatar = itemView.authorAvatar
    private val commentText = itemView.commentText

    fun onBind(itemUiModel: CommentUiModel) {
        name.text = itemUiModel.name
        author.text = itemUiModel.email
        commentText.text = itemUiModel.body
    }
}