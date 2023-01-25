package space.rozlach.myapplication.module.map.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import space.rozlach.myapplication.MainActivity
import space.rozlach.myapplication.R
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.module.map.model.Meteorite
import space.rozlach.myapplication.other.Constants.REQUEST_CODE_LOCATION_PERMISSIONS
import space.rozlach.myapplication.other.TrackingUtility


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener,
    EasyPermissions.PermissionCallbacks {

    private final  var myMap: GoogleMap? = null
    private var clusterManager: ClusterManager<Meteorite>? = null
    private lateinit var viewModel: MeteoriteViewModel
    private var meteoritesList = mutableListOf<Meteorite>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize view
//        requestPermission()
        val view: View = inflater.inflate(R.layout.fragment_map, container, false)
        viewModel = ViewModelProviders.of(this)[MeteoriteViewModel::class.java]

        // Initialize map fragment
        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment


        // Async map
        supportMapFragment.getMapAsync(this)
        // Return view
        return view
    }


    override fun onMapReady(map: GoogleMap) {
        myMap = map


//            myMap.apply {
        viewModel.meteoritesList.observe(activity as MainActivity, Observer {
            meteoritesList.addAll(it)
            meteoritesList.forEach {
                println(it.year)
                println(it.name)

            }
            for (i in 0 until meteoritesList.size) {
                val position = meteoritesList[i].position
                myMap!!.addMarker(
                    MarkerOptions()
                        .position(position)
                        //.title(name)
                        //.snippet(name.toString() + " is cool")
                        // .icon(BitmapDescriptorFactory
                        //       .fromResource(R.drawable.ic_map_pin )))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                )

            }
        })
//            }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            requestPermission()
            return
        }
        myMap!!.isMyLocationEnabled = true

    }

    override fun onStart() {
        super.onStart()
        if(myMap != null) {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
                myMap!!.isMyLocationEnabled = true
        }
    }

    private fun isLocationGranted(){

    }

    private fun requestPermission() {
        if (TrackingUtility.hasLocationPermission(activity as MainActivity))
            return
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app or some " +
                        "functions of the application will not be available.",
                REQUEST_CODE_LOCATION_PERMISSIONS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "You need to accept location permissions to use this app or some" +
                        " functions of the application will not be available.",
                REQUEST_CODE_LOCATION_PERMISSIONS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onCameraIdle() {
        TODO("Not yet implemented")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        } else
            requestPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

}