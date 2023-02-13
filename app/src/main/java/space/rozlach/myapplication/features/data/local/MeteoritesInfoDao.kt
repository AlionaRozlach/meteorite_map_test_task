package space.rozlach.myapplication.features.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import space.rozlach.myapplication.features.data.local.entity.MeteoriteEntity
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto

@Dao
interface MeteoritesInfoDao {
    @Query("SELECT * FROM meteorites_info WHERE year >= 2011 ORDER BY year")
    suspend fun getMeteoritesInfoList():List<MeteoriteEntity>

    @Query("SELECT * FROM meteorites_info WHERE year>= 2011 AND name == :name")
    suspend fun getInfoAboutMeteorite(name: String): MeteoriteEntity
    @Query("SELECT * FROM meteorites_info")
    suspend fun getAllDataFromDB(): MeteoriteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfMeteorites(meteoritesList: List<MeteoriteEntity>)

     @Query("DELETE FROM meteorites_info")
     suspend fun deleteAllFromTable()
}