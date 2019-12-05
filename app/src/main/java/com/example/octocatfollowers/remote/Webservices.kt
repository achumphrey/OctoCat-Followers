package com.example.octocatfollowers.remote

import com.example.octocatfollowers.model.FollowerRepo
import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservices {

    @GET(Constants.ENDPOINT_URL)
    fun getFollowers() : Single<List<Followers>>

    @GET(Constants.ENDPOINT_FOLLOWER_URL)
    fun getOneFollower(@Path("user") user: String) : Single<List<FollowerRepo>>
}