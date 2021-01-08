package org.shaun.itunessearch.viemodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.shaun.itunessearch.database.ItunesRoomDatabase
import org.shaun.itunessearch.modelclass.ResultModel
import org.shaun.itunessearch.repository.ItemRepository

class ITunesViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository: ItemRepository
    var itunesLiveData: MutableLiveData<ResultModel>?

    init {
        val dao=ItunesRoomDatabase.getDatabase(application).itunesDao()
        itemRepository=ItemRepository(dao)
        this.itunesLiveData = itemRepository.getResult("lorde")
    }

    fun getItemList() = this.itunesLiveData

    fun getNewData(queryNew: String) {
        itemRepository.getResult(queryNew)
        this.itunesLiveData?.postValue(itemRepository.data.value)
    }
}
