package space.rozlach.myapplication.module.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import space.rozlach.myapplication.module.map.model.Meteorite

@Dao
interface MeteoritesInfoDao {
    @Query("SELECT * FROM meteorites_info WHERE year >= 2011 ORDER BY year")
    fun getMeteoritesInfoList(): LiveData<List<Meteorite>>

    @Query("SELECT * FROM meteorites_info WHERE year>= 2011 AND name == :name")
    fun getInfoAboutMeteorite(name: String): LiveData<Meteorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListOfMeteorites(meteoritesList: List<Meteorite>)
}