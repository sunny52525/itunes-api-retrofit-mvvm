package org.shaun.itunessearch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "itunes_table")
@TypeConverters(ItunesTypeConverter::class)
data class Itunes(

    @PrimaryKey @ColumnInfo(name = "artist") val word: String,
    @TypeConverters(ItunesTypeConverter::class)
    @ColumnInfo(name = "itunes_data") val data:Dblist
) {}