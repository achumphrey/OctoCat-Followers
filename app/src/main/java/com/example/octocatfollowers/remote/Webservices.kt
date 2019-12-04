package com.example.octocatfollowers.remote

import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.util.Constants
import io.reactivex.Single
import retrofit2.http.GET

interface Webservices {

    @GET(Constants.ENDPOINT_URL)
    fun getFollowers() : Single<List<Followers>>
}