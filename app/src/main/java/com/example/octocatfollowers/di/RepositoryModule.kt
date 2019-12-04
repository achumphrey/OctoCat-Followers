package com.example.octocatfollowers.di

import com.example.octocatfollowers.remote.Webservices
import com.example.octocatfollowers.repository.FollowersRepoImp
import com.example.octocatfollowers.repository.FollowersRepository
import com.example.octocatfollowers.viewmodel.FollowersViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideViewModelFactory (repository: FollowersRepository): FollowersViewModelFactory {
        return  FollowersViewModelFactory(repository)
    }

    @Singleton
    @Provides
    fun provideFollowersRepository(webServices: Webservices): FollowersRepository{
        return FollowersRepoImp(webServices)
    }
}