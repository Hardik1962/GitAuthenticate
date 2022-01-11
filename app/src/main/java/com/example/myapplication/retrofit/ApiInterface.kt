package com.example.myapplication.retrofit

import com.example.myapplication.model.ReposResponseData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("repos")
    fun reposList(): Call<ArrayList<ReposResponseData>>
}