package com.example.octocatfollowers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.octocatfollowers.di.RepositoryModule
import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.repository.FollowersRepository
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class FollowersViewModel @Inject constructor(private val repository:FollowersRepository): ViewModel() {
    val disposable = CompositeDisposable()
    var followersList : MutableLiveData<List<Followers>> = MutableLiveData()
    var loadingState : MutableLiveData<LoadingState> = MutableLiveData()
    var errorMessage : MutableLiveData<String> = MutableLiveData()

    fun getFollowers(){
        loadingState.value = LoadingState.LOADING

        disposable.add(
        repository.getFollowersList()
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
    }


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


