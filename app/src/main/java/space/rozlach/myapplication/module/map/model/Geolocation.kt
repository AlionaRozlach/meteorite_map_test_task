package space.rozlach.myapplication.module.map.model

import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Geolocation (
    @SerializedName("type")
    @Expose
    private val type: String? = null,

)