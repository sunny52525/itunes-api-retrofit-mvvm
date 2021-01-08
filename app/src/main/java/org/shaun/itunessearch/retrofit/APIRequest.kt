package org.shaun.itunessearch.retrofit


import org.shaun.itunessearch.modelclass.ResultModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIRequest {
//    @Headers("Content-Type: application/json")
    @GET("search")
    fun getResult(@Query("term") query: CharSequence): Call<ResultModel>
}