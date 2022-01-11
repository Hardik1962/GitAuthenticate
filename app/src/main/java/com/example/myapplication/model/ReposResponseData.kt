package com.example.myapplication.model
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ReposResponseData {
    @SerializedName("id")
    var id: Int? = 0

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("full_name")
    var fullName: String? = ""

    @SerializedName("stargazers_count")
    var stargazers_count: Int? = 0
}