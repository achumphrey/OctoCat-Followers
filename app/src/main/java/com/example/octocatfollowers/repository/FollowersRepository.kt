package com.example.octocatfollowers.repository

import com.example.octocatfollowers.model.Followers
import io.reactivex.Single

interface FollowersRepository {
    fun getFollowersList(): Single<List<Followers>>
}