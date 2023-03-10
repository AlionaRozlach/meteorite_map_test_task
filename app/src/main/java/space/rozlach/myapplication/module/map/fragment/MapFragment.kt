package space.rozlach.myapplication.module.map.fragment

import android.Manifest
import android.annotation.SuppressLint
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.maps.android.clustering.ClusterManager
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import space.rozlach.myapplication.MainActivity
import space.rozlach.myapplication.R
import space.rozlach.myapplication.module.MeteoriteViewModel
import space.rozlach.myapplication.features.data.remote.dto.MeteoriteDto
import space.rozlach.myapplication.module.map.other.CustomMapTileProvider
import space.rozlach.myapplication.module.meteorites.adapter.MeteoritesAdapter
import space.rozlach.myapplication.module.meteorites.fragment.MeteoriteDetailFragment
import space.rozlach.myapplication.other.Constants.REQUEST_CODE_LOCATION_PERMISSIONS
import space.rozlach.myapplication.other.TrackingUtility


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener,
    EasyPermissions.PermissionCallbacks, MeteoritesAdapter.OnClick {

    private final var myMap: GoogleMap? = null
    private var clusterManager: ClusterManager<MeteoriteDto>? = null
    private lateinit var viewModel: MeteoriteViewModel
    private var meteoritesList = mutableListOf<MeteoriteDto>()
    var mBottomSheetBehavior2: BottomSheetBehavior<View>? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var meteoritesAdapter: MeteoritesAdapter
    private var list = ArrayList<MeteoriteDto>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize view
//        requestPermission()

        val view: View = inflater.inflate(R.layout.fragment_map, container, false)
        viewModel = ViewModelProviders.of(requireActivity())[MeteoriteViewModel::class.java]

        val supportMapFragment =
            childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment

        val bottomSheet: View = view.findViewById(R.id.bottom_sheet2)
        mBottomSheetBehavior2 = BottomSheetBehavior.from(bottomSheet)


        mBottomSheetBehavior2!!.peekHeight = 300;


        mBottomSheetBehavior2!!.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // React to state change
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    mBottomSheetBehavior2!!.state = BottomSheetBehavior.STATE_EXPANDED
                }


            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // React to dragging events
            }

        })




        viewModel.getMeteoritesListVM().observe(activity as MainActivity, Observer { meteorites ->
            list.removeAll(list)
            // update with the new events that we have observed.
            list.addAll(meteorites)
            // tell the recycler view to update.
            recyclerView.adapter!!.notifyDataSetChanged()
        })


        recyclerView = view.findViewById(R.id.meteoritesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MeteoritesAdapter(this, list)

        supportMapFragment.getMapAsync(this)

        return view
    }

    override fun onMapReady(map: GoogleMap) {
        myMap = map
        viewModel.meteoritesList.observe(activity as MainActivity, Observer {
            meteoritesList.addAll(it)
            for (i in 0 until meteoritesList.size) {
                val position = meteoritesList[i].position
                myMap!!.addMarker(
                    MarkerOptions()
                        .position(position)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
                )

            }
        })


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

        myMap!!.addTileOverlay(TileOverlayOptions().tileProvider(CustomMapTileProvider(resources.assets)))

    }

    override fun onStart() {
        super.onStart()
        if (myMap != null) {
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

    override fun onClick(meteoriteDto: MeteoriteDto) {
        viewModel.selectItem(meteoriteDto)

        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, MeteoriteDetailFragment(), null)
            .addToBackStack("")
            .commit()
    }

}