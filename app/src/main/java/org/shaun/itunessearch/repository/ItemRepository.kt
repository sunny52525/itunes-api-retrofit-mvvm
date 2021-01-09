package org.shaun.itunessearch.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.shaun.itunessearch.dao.ItunesDao
import org.shaun.itunessearch.database.DBWrapper
import org.shaun.itunessearch.database.Itunes
import org.shaun.itunessearch.modelclass.ResultModel
import org.shaun.itunessearch.retrofit.APIRequest
import org.shaun.itunessearch.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ItemRepository"

class ItemRepository(val dao: ItunesDao) {
    private var apiRequest: APIRequest = Retrofit.getRetrofit().create(APIRequest::class.java)
    val data = MutableLiveData<ResultModel>()
    fun getResult(query: String): MutableLiveData<ResultModel>? {

        apiRequest.getResult(query)
            .enqueue(object : Callback<ResultModel> {
                override fun onFailure(call: Call<ResultModel>, t: Throwable) {
                    Log.d(TAG, "onFailure: $t")


                    GlobalScope.launch {
                        val offLineData: DBWrapper? = dao.getOfflineData(query)
                        if (offLineData != null) {
                            val resultModel = ResultModel()
                            resultModel.results = offLineData.list
                            data.postValue(resultModel)
                            Log.d(TAG, "onFailure: $data.")
                        } else {
                            data.postValue(ResultModel())  //no Data
                        }

                    }
                }

                override fun onResponse(call: Call<ResultModel>, response: Response<ResultModel>) {
                    data.value = response.body()


                    //Saving query in database for offline access in case of no internet or some failure

                    if (data.value?.results?.size!=0) { //Saving only those query which return non null values
                        val itunesItem = Itunes(query, (DBWrapper(data.value?.results!!)))
                        GlobalScope.launch {
                            dao.insert(itunesItem)
                        }
                    }


                }

            })
        return data
    }
}