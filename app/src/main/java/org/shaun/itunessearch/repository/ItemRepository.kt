package org.shaun.itunessearch.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.shaun.itunessearch.dao.ItunesDao
import org.shaun.itunessearch.database.Dblist
import org.shaun.itunessearch.database.Itunes
import org.shaun.itunessearch.modelclass.ResultModel

import org.shaun.itunessearch.retrofit.APIRequest
import org.shaun.itunessearch.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ItemRepository"

class ItemRepository(val dao: ItunesDao) {

    var apiRequest: APIRequest = Retrofit.getRetrofit().create(APIRequest::class.java)
    val data = MutableLiveData<ResultModel>()
    fun getResult(query: String): MutableLiveData<ResultModel>? {

        apiRequest.getResult(query)
            .enqueue(object : Callback<ResultModel> {
                override fun onFailure(call: Call<ResultModel>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")
                    GlobalScope.launch {

                    val offLineData = dao.getOfflineData(query)
                    Log.e(TAG, "onFailure: ${offLineData.list}")
                        val resultModel=ResultModel()
                        resultModel.results=offLineData.list
                    if (offLineData!= null) {
                        data.postValue(resultModel)

                        Log.d(TAG, "onFailure: $data.")
                    }else{
                        Log.e(TAG, "onFailure: 0000000000" )
                    }

                    }
                }

                override fun onResponse(call: Call<ResultModel>, response: Response<ResultModel>) {
                    data.value = response.body()

                    val itunesItem= Itunes(query, (Dblist(data.value?.results!!))!!)
                    GlobalScope.launch {
                        dao.insert(itunesItem)
                    }
                    Log.d(TAG, "onResponse: ${response.body()?.results}")

                }

            })
        return data
    }
}