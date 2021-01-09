package org.shaun.itunessearch.database

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import org.json.JSONObject
import org.shaun.itunessearch.modelclass.ITunesItem

private const val TAG = "ItunesTypeConverter"

class ItunesTypeConverter {
    private val gSon = Gson()

    @TypeConverter
    fun stringToDBWrapper(result: String): DBWrapper {


        // For Some reason this wasn't working so I wrote my own Json Parser

        //val listType = object : TypeToken<DBWrapper>() {}.type
        //  return gSon.fromJson(result, listType)


        val itunesList: MutableList<ITunesItem> = arrayListOf()
        try {
            val json = JSONObject(result)
            val jsonArray = json.getJSONArray("list")
            for (i in 0 until jsonArray.length()) {
                val item = jsonArray.getJSONObject(i)
                val artistName = item.getString("artistName")
                val artworkUrl100 = item.getString("artworkUrl100")
                val collectionName = item.getString("collectionName")
                val wrapperType = item.getString("wrapperType")
                val itunesItem = ITunesItem(wrapperType, artistName, collectionName, artworkUrl100)
                itunesList.add(itunesItem)
            }
        } catch (e: Exception) {
            Log.e(TAG, "stringToDBWrapper: $e")
        }
        return DBWrapper(itunesList)
    }

    @TypeConverter
    fun dBWrapperToString(result: DBWrapper): String {
        return gSon.toJson(result)
    }

}