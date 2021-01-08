package org.shaun.itunessearch.database

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import org.json.JSONArray
import org.shaun.itunessearch.modelclass.ITunesItem

private const val TAG = "ItunesTypeConverter"
class ItunesTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun DblistToiTunes(result: String): org.shaun.itunessearch.database.Dblist {
        val maxLogSize = 1000
        for (i in 0..result.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > result.length) result.length else end
            Log.v(TAG, result.substring(start, end))
        }
//        val listType = object : TypeToken<Dblist>() {}.type
//        return gson.fromJson(result, listType)

        val json: JSONArray =JSONArray(result)
        var itunesList:MutableList<ITunesItem> = arrayListOf()
        for (i in 0 until json.length()){
            val item=json.getJSONObject(i)
            val artistName=item.getString("artistName")
//            val artistViewUrl:String?=item.getString("artistViewUrl")
            val artworkUrl100=item.getString("artworkUrl100")
            val collectionName=item.getString("collectionName")
            val wrapperType=item.getString("wrapperType")
            val itunesItem=ITunesItem(wrapperType,artistName,collectionName,"artistViewUrl",artworkUrl100)
            itunesList.add(itunesItem)
        }
        val dbList=Dblist(itunesList)
        Log.d(TAG, "DblistToiTunes: $itunesList")
        return dbList
    }

    @TypeConverter
    fun DblistToString(result: org.shaun.itunessearch.database.Dblist): String {
        return gson.toJson(result)
    }

  }