package space.rozlach.myapplication.features.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import space.rozlach.myapplication.features.domain.model.Meteorite

@Entity(tableName = "meteorites_info")
data class MeteoriteEntity(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("nametype")
    @Expose
    var nameType: String? = null,

    @SerializedName("recclass")
    @Expose
    var recClass: String? = null,

    @SerializedName("mass")
    @Expose
    var mass: String? = null,

    @SerializedName("fall")
    @Expose
    var fall: String? = null,

    @SerializedName("year")
    @Expose
    var year: String? = null,

    @SerializedName("reclat")
    @Expose
    var recLat: String? = null,

    @SerializedName("reclong")
    @Expose
    var recLong: String? = null,
){
    fun toMeteorite(): Meteorite {

        return Meteorite(
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