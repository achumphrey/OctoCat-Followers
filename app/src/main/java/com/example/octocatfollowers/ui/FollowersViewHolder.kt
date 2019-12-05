package com.example.octocatfollowers.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.octocatfollowers.model.FollowerRepo
import com.example.octocatfollowers.model.Followers
import kotlinx.android.synthetic.main.followers_holder_images.view.*

class FollowersViewHolder(item:View): RecyclerView.ViewHolder(item) {
    fun bindItem(followers: FollowerRepo){
        itemView.tvLogin.text = followers.id.toString()
     //   itemView.tvRepoUrl.text = followers.reposUrl
        }
}