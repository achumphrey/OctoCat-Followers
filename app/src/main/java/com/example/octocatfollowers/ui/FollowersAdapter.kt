package com.example.octocatfollowers.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.octocatfollowers.R
import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.util.inflate

class FollowersAdapter constructor(private var followers: MutableList<Followers>):
    RecyclerView.Adapter<FollowersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val view : View = parent.inflate(R.layout.followers_holder_images, false)
        return FollowersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        holder.bindItem(followers[position])
    }

    fun updateList(newList: List<Followers>){
        followers.clear()
        followers.addAll(newList)
        notifyDataSetChanged()
    }
}