package org.shaun.itunessearch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.shaun.itunessearch.dao.ItunesDao

@Database(entities = [Itunes::class], version = 1)
@TypeConverters(ItunesTypeConverter::class)
abstract class ItunesRoomDatabase : RoomDatabase() {

    abstract fun itunesDao(): ItunesDao

    companion object {
        @Volatile
        private var INSTANCE: ItunesRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): ItunesRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItunesRoomDatabase::class.java,
                    "itunes_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }


}