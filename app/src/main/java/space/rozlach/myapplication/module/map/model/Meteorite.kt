package space.rozlach.myapplication.module.map.model

import androidx.annotation.NonNull
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "meteorites_info")
data class Meteorite(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: String,

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

//    @SerializedName("geolocation")
//    @Expose
//    @Embedded
//    var geolocation: Geolocation? = null
)