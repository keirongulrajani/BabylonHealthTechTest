package com.keiron.techtest.section.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keiron.techtest.R
import com.keiron.techtest.section.details.model.CommentUiModel

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {

    private val uiModels: MutableList<CommentUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uiModels.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.onBind(uiModels[position])
    }

    fun updateUiModels(uiModels: List<CommentUiModel>) {
        this.uiModels.clear()
        this.uiModels.addAll(uiModels)

        notifyDataSetChanged()
    }
}