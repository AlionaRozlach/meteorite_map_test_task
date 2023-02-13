package space.rozlach.myapplication.features.domain.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem


data class Meteorite(

    var name: String? = null,
//    var id: String,
    var nameType: String? = null,
    var mass: String? = null,
    var fall: String? = null,
    var year: String? = null,
    var recLat: String? = null,
    var recLong: String? = null,
) : ClusterItem {
    override fun getPosition(): LatLng {
        return LatLng(recLat!!.fullTrim().toDouble(), recLong!!.fullTrim().toDouble())
    }

    override fun getTitle(): String? {
        TODO("Not yet implemented")
    }

    override fun getSnippet(): String? {
        TODO("Not yet implemented")
    }

    private fun String.fullTrim() = trim().replace("\uFEFF", "")
}