package proj.kolot.com.nearshops

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.CustomEvent
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import proj.kolot.com.nearshops.data.FoundedPlace
import proj.kolot.com.nearshops.data.GeneralResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val PROXIMITY_RADIUS = 500
        private const val TYPE_OBJECT = "shopping_mall"
        private const val ZOOM_VALUE = 15f
        private const val TAG = "MapsActivity"
    }

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        showContent()
        //for test Crashlytics
       //throw RuntimeException("This is a crash")
    }


    private fun checkLocationPermission(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE)
                return false;
            } else {
                return true;
            }
        } else {
            return true
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.size > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showContent()
                }
            }
        }

    }

    override fun onMapReady(map: GoogleMap) {
        this.map = map
        if (checkLocationPermission()) {
            this.map.setMyLocationEnabled(true)
            map.getUiSettings().setMyLocationButtonEnabled(true)

        } else {
            this.map.setMyLocationEnabled(false)
            map.getUiSettings().setMyLocationButtonEnabled(false)
        }
        showContent()
    }


    private fun showContent() {
        try {
            if (checkLocationPermission()) {
                val locationResult = fusedLocationClient.getLastLocation()
                locationResult.addOnSuccessListener(this) { location ->
                    if (location != null) {
                        lastLocation = location
                        val currentLatLng = LatLng(location.latitude, location.longitude)
                        showPoint(getString(R.string.my_location_title), currentLatLng)
                        findShops()
                    }
                }

            }
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }

    }

    override fun onMarkerClick(p0: Marker?) = false

    private fun findShops() {
        sendMetrics()
        App.getInstance().nearbyApi.getNearByPlaces(getLatLong(), PROXIMITY_RADIUS, TYPE_OBJECT, getString(R.string.google_maps_key)).enqueue(object : Callback<GeneralResponse> {
            override fun onResponse(call: Call<GeneralResponse>?, response: Response<GeneralResponse>?) {
                showNearbyPlaces(response?.body()?.foundedPlaces?.toList() ?: emptyList())
            }

            override fun onFailure(call: Call<GeneralResponse>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }

        })
    }

    //for test Answers
    private fun sendMetrics() {
            Answers.getInstance().logCustom(CustomEvent("Find Shop"))

    }

    private fun getLatLong(): String {
        return lastLocation.latitude.toString() + "," + lastLocation.longitude
    }


    private fun showNearbyPlaces(nearbyFoundedPlaceList: List<FoundedPlace>) {
        nearbyFoundedPlaceList.forEach {
            val title = it.name + " : " + it.vicinity;
            val latLng = LatLng(it.geometry?.location?.lat?:0.0, it.geometry?.location?.lng?:0.0)
            showPoint(title, latLng)


        }
    }

    private fun showPoint(name: String, position: LatLng) {
        var markerOptions = MarkerOptions()
        markerOptions.position(position)
        markerOptions.title(name)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        map.addMarker(markerOptions)
        map.moveCamera(CameraUpdateFactory.newLatLng(position))
        map.animateCamera(CameraUpdateFactory.zoomTo(ZOOM_VALUE))
    }


}
