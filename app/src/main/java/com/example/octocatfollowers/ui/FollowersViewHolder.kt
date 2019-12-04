package com.example.octocatfollowers.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.octocatfollowers.model.Followers
import kotlinx.android.synthetic.main.followers_holder_images.view.*

class FollowersViewHolder(item:View): RecyclerView.ViewHolder(item) {
    fun bindItem(followers: Followers){
        itemView.tvLogin.text = followers.login
        itemView.tvRepoUrl.text = followers.reposUrl
        }
}