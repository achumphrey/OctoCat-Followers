package com.example.octocatfollowers.di

import com.example.octocatfollowers.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules= [RepositoryModule::class, WebServicesModule::class])
interface FollowersComponent {
    fun inject(mainActivity: MainActivity)
}