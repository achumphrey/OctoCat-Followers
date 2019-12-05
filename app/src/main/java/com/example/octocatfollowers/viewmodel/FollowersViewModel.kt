package com.example.octocatfollowers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.octocatfollowers.di.RepositoryModule
import com.example.octocatfollowers.model.FollowerRepo
import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.repository.FollowersRepository
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class FollowersViewModel @Inject constructor(private val repository:FollowersRepository): ViewModel() {
    val disposable = CompositeDisposable()
    var followersList : MutableLiveData<List<Followers>> = MutableLiveData()
    var followerList : MutableLiveData<List<FollowerRepo>> = MutableLiveData()
    var loadingState : MutableLiveData<LoadingState> = MutableLiveData()
    var errorMessage : MutableLiveData<String> = MutableLiveData()

    fun getFollowers(){
        loadingState.value = LoadingState.LOADING

        disposable.add(
        repository.getFollowersList()
            .map { it.distinctBy { it.login } }
            .map { (res) -> res.login  }
            .flatMap {user-> repository.getOneFollower(user)  }
            .map { it.subList(0,3) }
            .subscribe({
                followerList.value = it
                loadingState.value = LoadingState.SUCCESS
            },{
                it.printStackTrace()
            }))
    }

    /*
    if you need unique based on login then it should be like

repository.getFollowersList()
.distinct { it.login }
.map { (res) -> res.login }
     */

    /*
     Observable.just(retrofit.create(StoreCouponsApi.class))
          .subscribeOn(Schedulers.computation())
          .flatMap(s -> {
                 return s.getStoreInfo().subscribeOn(Schedulers.io())
                         .map(res -> res.getStore())
                         .flatMap( store -> s.getCoupons(store));
           }).observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResults, this::handleError );

     */


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    enum class LoadingState {
        ERROR,
        SUCCESS,
        LOADING
    }
}

/*
.subscribe({
                if (it.isEmpty()){
                    loadingState.value = LoadingState.ERROR
                    errorMessage.value = "No Data Found"
                    }else{
                    loadingState.value = LoadingState.SUCCESS
                    followersList.value = it
                }
        },{
                it.printStackTrace()
                when (it) {
                    is UnknownHostException -> errorMessage.value = "No Network"
                    else -> errorMessage.value = it.localizedMessage
                }
                loadingState.value = LoadingState.ERROR
            })
        )
 */
