package com.example.octocatfollowers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.octocatfollowers.repository.FollowersRepository

class FollowersViewModelFactory (private val repository: FollowersRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FollowersViewModel(repository) as T
    }

}
