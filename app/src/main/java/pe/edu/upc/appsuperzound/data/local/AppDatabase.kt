package pe.edu.upc.appsuperzound.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [AlbumEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    companion object{

        private var INSTANCE: AppDatabase?=null

        fun getInstance(context: Context): AppDatabase{
            INSTANCE = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "album.db"
            )
                .allowMainThreadQueries()
                .build()
            return INSTANCE as AppDatabase
        }

    }

}