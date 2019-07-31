package com.example.githubtraininappkotlin.data

import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiInterface {

@GET("user")
fun getOwner(@Header("Authorization") authHeader: String): Call<OwnerEntity>

    @GET("user/repos")
    fun getRepos(@Header("Authorization") authHeader: String): Call<List<GithubRepoEntity>>
}