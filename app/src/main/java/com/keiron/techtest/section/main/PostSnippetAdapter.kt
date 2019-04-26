package com.keiron.techtest.section.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.keiron.techtest.R
import com.keiron.techtest.section.main.model.MainUiModel

class PostSnippetAdapter constructor(val onPostClickedListener: OnPostClickedListener) :
    RecyclerView.Adapter<PostSnippetViewHolder>() {

    private val uiModels: MutableList<MainUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostSnippetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_post, parent, false)
        return PostSnippetViewHolder(view)
    }

    override fun getItemCount(): Int {
        return uiModels.size
    }

    override fun onBindViewHolder(holder: PostSnippetViewHolder, position: Int) {
        holder.onBind(uiModels[position], onPostClickedListener)
    }

    fun updateUiModels(uiModels: List<MainUiModel>) {
        this.uiModels.clear()
        this.uiModels.addAll(uiModels)

        notifyDataSetChanged()
    }
}