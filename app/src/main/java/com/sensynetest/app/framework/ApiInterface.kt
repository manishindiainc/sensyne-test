package com.example.kotlinproj.framework

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("data/foi/Hospital.csv")
    suspend fun download(): Response<ResponseBody>
}