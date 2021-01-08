package org.shaun.itunessearch.viemodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.shaun.itunessearch.modelclass.ResultModel
import org.shaun.itunessearch.repository.ItemRepository

private const val TAG = "ITunesViewModel"

class ITunesViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository: ItemRepository = ItemRepository()
    var itunesLiveData: MutableLiveData<ResultModel>?

    init {

        this.itunesLiveData = itemRepository.getResult("lorde")
    }

    fun getItemList() = itunesLiveData

    fun getNewData(queryNew: String) {
        Log.d(TAG, "getNewData: $queryNew")
        itemRepository.getResult(queryNew)
        Log.d(TAG, "getNewData: ${itunesLiveData?.value?.results}")
    }
}
