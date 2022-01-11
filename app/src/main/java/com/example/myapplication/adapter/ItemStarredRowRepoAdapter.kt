package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ReposResponseData
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.item_row_repo.view.*

class ItemStarredRowRepoAdapter(val starredrepoList: ArrayList<ReposResponseData>): RecyclerView.Adapter<ItemStarredRowRepoAdapter.ItemRowViewholder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRowViewholder {
        return ItemRowViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_repo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemRowViewholder, position: Int) {
        val list = starredrepoList[position]
        holder.reponame.text = list.name
    }

    override fun getItemCount(): Int {
        return starredrepoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list: ArrayList<ReposResponseData>){
        starredrepoList.addAll(list)
        notifyDataSetChanged()
    }

    class ItemRowViewholder(view: View) :RecyclerView.ViewHolder(view){
        val reponame:MaterialTextView = view.tv_repoName
    }

}