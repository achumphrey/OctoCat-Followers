package com.example.octocatfollowers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.octocatfollowers.R
import com.example.octocatfollowers.di.DaggerFollowersComponent
import com.example.octocatfollowers.di.RepositoryModule
import com.example.octocatfollowers.di.WebServicesModule
import com.example.octocatfollowers.viewmodel.FollowersViewModel
import com.example.octocatfollowers.viewmodel.FollowersViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: FollowersViewModelFactory
    lateinit var viewModel: FollowersViewModel
    lateinit var followersAdapter: FollowersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDependency()
        setupRecyclerView()

        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FollowersViewModel::class.java)

        viewModel.followerList .observe(this, Observer {
            followersAdapter.updateList(it)

        })

        viewModel.errorMessage.observe(this, Observer {
            tvErrorMessage.text = it
        })

        viewModel.loadingState.observe(this, Observer {
            when (it) {
                FollowersViewModel.LoadingState.LOADING -> displayProgressbar()
                FollowersViewModel.LoadingState.SUCCESS -> displayImageList()
                FollowersViewModel.LoadingState.ERROR -> displayErrorMessage()
                else -> displayErrorMessage()
            }
        })

        viewModel.getFollowers()
    }

    fun getDependency() {
        DaggerFollowersComponent.builder()
            .repositoryModule(RepositoryModule())
            .webServicesModule(WebServicesModule())
            .build()
            .inject(this)
    }

    private fun displayProgressbar() {
        prgBar.visibility = View.VISIBLE
        rvFollowers.visibility = View.GONE
        tvErrorMessage.visibility = View.GONE
    }

    private fun displayErrorMessage() {
        tvErrorMessage.visibility = View.VISIBLE
        rvFollowers.visibility = View.GONE
        prgBar.visibility = View.GONE
    }

    private fun displayImageList() {

        tvErrorMessage.visibility = View.GONE
        rvFollowers.visibility = View.VISIBLE
        prgBar.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        rvFollowers.layoutManager = LinearLayoutManager(this)
        followersAdapter = FollowersAdapter(mutableListOf())
        rvFollowers.adapter = followersAdapter
    }
}
