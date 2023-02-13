package space.rozlach.myapplication.features.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.google.maps.android.clustering.ClusterItem
import space.rozlach.myapplication.features.data.local.entity.MeteoriteEntity
import space.rozlach.myapplication.features.domain.model.Meteorite

data class MeteoriteDto(
    var name: String? = null,
    var id: String,
    var nameType: String? = null,
    var recClass: String? = null,
    var mass: String? = null,
    var fall: String? = null,
    var year: String? = null,
    var recLat: String? = null,
    var recLong: String? = null,
) {
    fun toMeteoriteEntity(): MeteoriteEntity {
        return MeteoriteEntity(
            name = name,
            nameType = nameType,
            mass = mass,
            fall = fall,
            year = year,
            recLat = recLat,
            recLong = recLong
        )
    }
}

