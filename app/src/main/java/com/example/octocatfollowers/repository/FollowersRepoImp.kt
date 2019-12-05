package com.example.octocatfollowers.repository

import com.example.octocatfollowers.model.FollowerRepo
import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.remote.Webservices
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FollowersRepoImp @Inject constructor(private val webServices: Webservices):
    FollowersRepository {

    override fun getFollowersList(): Single<List<Followers>> {
        return webServices.getFollowers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getOneFollower(user:String): Single<List<FollowerRepo>> {
        return webServices.getOneFollower(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}