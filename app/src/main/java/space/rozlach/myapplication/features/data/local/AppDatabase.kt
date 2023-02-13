package space.rozlach.myapplication.features.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto

@Database(entities = [MeteoriteDto::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: MeteoritesInfoDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()
        fun getInstance(context: Context): AppDatabase {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun meteoritesInfoDao(): MeteoritesInfoDao
}