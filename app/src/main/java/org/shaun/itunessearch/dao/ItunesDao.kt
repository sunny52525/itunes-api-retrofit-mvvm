package org.shaun.itunessearch.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.shaun.itunessearch.database.DBWrapper
import org.shaun.itunessearch.database.Itunes

@Dao
interface ItunesDao {

    @Query(value = "SELECT itunes_data FROM itunes_table where artist IN (:artistName)")
    fun getOfflineData(artistName: String): DBWrapper

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itunesData:Itunes)

}