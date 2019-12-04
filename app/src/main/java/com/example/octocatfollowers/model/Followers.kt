package com.example.octocatfollowers.model


import com.google.gson.annotations.SerializedName

data class Followers(
    @SerializedName("id")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("repos_url")
    val reposUrl: String
)