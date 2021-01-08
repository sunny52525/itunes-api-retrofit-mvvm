package org.shaun.itunessearch.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.shaun.itunessearch.database.Dblist
import org.shaun.itunessearch.database.Itunes
//SELECT itunes_data from itunes_table where artist IN ("lorde");
//SELECT itunes_data FROM itunes_table where artist IN ('lorde');
@Dao
interface ItunesDao {

    @Query(value = "SELECT itunes_data FROM itunes_table where artist IN (:artistName)")

    fun getOfflineData(artistName: String): Dblist

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itunesData:Itunes)

}