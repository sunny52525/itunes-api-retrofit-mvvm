package org.shaun.itunessearch.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import org.json.JSONArray
import org.shaun.itunessearch.modelclass.ITunesItem

class ItunesTypeConverter {
    val gSon = Gson()

    @TypeConverter
    fun stringToDBWrapper(result: String): DBWrapper {


//        For Some reason this wasn't working so I wrote my own Json Parser

        /**
         *  val listType = object : TypeToken<DBWrapper>() {}.type
         *  return gSon.fromJson(result, listType)
         */


        val json =JSONArray(result)
        val itunesList:MutableList<ITunesItem> = arrayListOf()
        for (i in 0 until json.length()){
            val item=json.getJSONObject(i)
            val artistName=item.getString("artistName")
            val artworkUrl100=item.getString("artworkUrl100")
            val collectionName=item.getString("collectionName")
            val wrapperType=item.getString("wrapperType")
            val itunesItem=ITunesItem(wrapperType,artistName,collectionName,artworkUrl100)
            itunesList.add(itunesItem)
        }
        return DBWrapper(itunesList)
    }

    @TypeConverter
    fun dBWrapperToString(result: DBWrapper): String {
        return gSon.toJson(result)
    }

  }