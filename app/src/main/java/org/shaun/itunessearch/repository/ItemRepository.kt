package org.shaun.itunessearch.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import org.shaun.itunessearch.modelclass.ResultModel
import org.shaun.itunessearch.retrofit.APIRequest
import org.shaun.itunessearch.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ItemRepository"
class ItemRepository{

    var apiRequest:APIRequest = Retrofit.getRetrofit().create(APIRequest::class.java)

    fun getResult(query:String) :MutableLiveData<ResultModel>?{
        val data=MutableLiveData<ResultModel>()
        apiRequest.getResult(query)
            .enqueue(object : Callback<ResultModel> {
                override fun onFailure(call: Call<ResultModel>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                }
                override fun onResponse(call: Call<ResultModel>, response: Response<ResultModel>) {
                   data.value =response.body()
                    Log.d(TAG, "onResponse: ${response.body()?.results}")

                }

            })
        return data
    }
}