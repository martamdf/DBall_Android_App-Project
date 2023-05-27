package com.example.basicapp.UI

//import android.Manifest
//import android.Manifest
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.basicapp.R
import com.example.basicapp.UI.model.SuperheroLocations
import com.example.basicapp.databinding.FragmentSecondBinding
import com.example.basicapp.utils.viewBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import java.util.Locale


@AndroidEntryPoint
class SecondFragment : Fragment(R.layout.fragment_second), OnMapReadyCallback {

    private val binding: FragmentSecondBinding by viewBinding(FragmentSecondBinding::bind)
    private val args: SecondFragmentArgs by navArgs()

    private val viewModel: SuperHeroViewModel by viewModels()

    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.getHero(args.superheroId)

        viewModel.hero.observe(viewLifecycleOwner){ hero ->
            binding.textviewSecond.text = hero.name
            Picasso.get().load(hero.photo).into(binding.heroImageView)
            hero.locations?.let { loadLocationsInMap(it) }

        }

/*
        binding.button.setOnClickListener {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(40.0, -3.6), 20.0F))

            if (checkPermission()){
                showLocation()
            }
        }*/
    }

    @SuppressLint("MissingPermission")
    private fun showLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        fusedLocationClient.lastLocation.addOnCompleteListener{
            if (it.isSuccessful){
                val location = it.result
                val marker = MarkerOptions().position(LatLng(location.latitude, location.longitude))
                map.addMarker(marker)
            }
        }
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val hasPermission = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            // get location
        } else {
            val permissionLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                    if (isGranted) {
                        //getLocation
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "No podemos mostrar la localizacion sin permiso",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap

        // Add a marker in Sydney and move the camera
/*        val sydney = LatLng(-34.0, 151.0)
        //val madrid = LatLng(40.0, -3.6)
        map.addMarker(
            MarkerOptions()
                .position(sydney)
        )*/

        //map.addPolyline(PolylineOptions().addAll(listOf(sydney, madrid)).color(Color.BLUE).width(100F))
//        map.setOnMarkerClickListener {
//            //binding.customMarker.text = "Sydney"
//            binding.customMarker.isVisible = true
//            true
//        }


        //map.addCircle(CircleOptions().center(sydney).radius(10.0).fillColor(Color.BLACK))
        //map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private fun loadLocationsInMap(locations: List<SuperheroLocations>) {
        if (this::map.isInitialized){
            for (location in locations) {

                var countryName = " Cerca de "
                try {
                    val gcd = Geocoder(requireContext(), Locale.getDefault())
                    val addresses: List<Address>? = gcd.getFromLocation(location.latitude.toDouble(), location.longitude.toDouble(), 1)
                    if (addresses!!.size > 0) {
                        countryName += addresses!![0].locality + ", " + addresses!![0].getCountryName()
                    }
                }catch (e: Exception){
                    countryName = ""
                }

                val latlng = LatLng(location.latitude.toDouble(), location.longitude.toDouble())
                map.addMarker(
                    MarkerOptions()
                        .position(latlng)
                        .title("Visto por última vez: " + location.dateShow)
                        .snippet(countryName))
            }
            if (locations.isNotEmpty()) {
                val lastCoordinates = LatLng(locations.last().latitude.toDouble(), locations.last().longitude.toDouble())
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastCoordinates, 2f))
            }
        }
    }
}