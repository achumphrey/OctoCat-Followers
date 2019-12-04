package com.example.octocatfollowers.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.octocatfollowers.model.Followers
import com.example.octocatfollowers.repository.FollowersRepository
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.net.UnknownHostException

@RunWith(MockitoJUnitRunner::class)
class FollowersViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var followersViewModel: FollowersViewModel
    private var listFollowers = mutableListOf<Followers>()
    private val followersListLDObserver: Observer<List<Followers>> = mock()
    private val errorMessageLDObsrever: Observer<String> = mock()
    private val loadingStateLDObserver: Observer<FollowersViewModel.LoadingState> = mock()
    private val followers: Followers = Followers(100, "anything", "anything")

    @Mock
    lateinit var repository: FollowersRepository

    @Before
    fun setUp() {
        followersViewModel = FollowersViewModel(repository)
        listFollowers.add(followers)
        followersViewModel.followersList.observeForever(followersListLDObserver)
        followersViewModel.errorMessage.observeForever(errorMessageLDObsrever)
        followersViewModel.loadingState.observeForever(loadingStateLDObserver)
    }

    @Test
    fun fetchFollowers_ReturnList_WithSuccess() {

        `when`(repository.getFollowersList()).thenReturn(Single.just(listFollowers))

        followersViewModel.getFollowers()

        verify(repository, atLeast(1)).getFollowersList()
        verify(followersListLDObserver, atLeast(1)).onChanged(listFollowers)
        verify(errorMessageLDObsrever, atLeast(0)).onChanged("some message")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(FollowersViewModel.LoadingState.SUCCESS)
    }

    @Test
    fun fetchFollowers_Return_EmptyList() {

        val followersList: List<Followers> = emptyList()

        `when`(repository.getFollowersList()).thenReturn(Single.just(followersList))

        followersViewModel.getFollowers()

        verify(repository, atLeast(1)).getFollowersList()
        verify(followersListLDObserver, atLeast(0)).onChanged(emptyList())
        verify(errorMessageLDObsrever, atLeast(1)).onChanged("No Data Found")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(FollowersViewModel.LoadingState.ERROR)
    }

    @Test
    fun fetchFellowers_NoReturn_NoNetwork() {

        `when`(repository.getFollowersList()).thenReturn(Single.error(UnknownHostException("No Network")))

        followersViewModel.getFollowers()

        verify(repository, atLeast(1)).getFollowersList()
        verify(followersListLDObserver, atLeast(0)).onChanged(emptyList())
        verify(errorMessageLDObsrever, atLeast(1)).onChanged("No Network")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(FollowersViewModel.LoadingState.ERROR)
    }

    @Test
    fun fetchFellowers_NoReturn_ErrorOccurred() {

        `when`(repository.getFollowersList()).thenReturn(Single.error(RuntimeException("Something Wrong")))

        followersViewModel.getFollowers()

        verify(repository, atLeast(1)).getFollowersList()
        verify(followersListLDObserver, atLeast(0)).onChanged(emptyList())
        verify(errorMessageLDObsrever, atLeast(1)).onChanged("Something Wrong")
        verify(loadingStateLDObserver, atLeast(1)).onChanged(FollowersViewModel.LoadingState.ERROR)
    }

    @After
    fun tearDown() {
    }
}