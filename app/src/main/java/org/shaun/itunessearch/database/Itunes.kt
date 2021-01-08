package org.shaun.itunessearch.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "itunes_table")
data class Itunes(
    @PrimaryKey @ColumnInfo(name = "artist") val word: String,
    @ColumnInfo(name = "itunes_data") val data:DBWrapper
) {}