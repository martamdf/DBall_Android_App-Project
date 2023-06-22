package com.example.basicapp.ui.heroes.herodetail

import android.Manifest
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
import com.example.basicapp.databinding.FragmentHeroDetailBinding
import com.example.basicapp.ui.heroes.model.Superhero
import com.example.basicapp.ui.heroes.model.SuperheroLocations
import com.example.basicapp.utils.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class HeroDetailFragment : Fragment(R.layout.fragment_hero_detail), OnMapReadyCallback {

    private val binding: FragmentHeroDetailBinding by viewBinding(FragmentHeroDetailBinding::bind)
    private val args: HeroDetailFragmentArgs by navArgs()

    private val viewModel: SuperHeroViewModel by viewModels()
    private lateinit var map: GoogleMap

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel.getHero(args.superheroId)

        with(binding){
            viewModel.hero.observe(viewLifecycleOwner){ hero ->
                textviewSecond.text = hero.name
                Picasso.get().load(hero.photo).into(heroImageView)

                if(hero.favorite){
                    isFav.setImageResource(R.drawable.heart_fill_frame)
                }else{
                    isFav.setImageResource(R.drawable.heart_empty_frame)
                }

                hero.locations?.let { loadLocationsInMap(it) }

                isFav.setOnClickListener {
                    setFav(hero)
                }
            }
        }
    }

    private fun setFav(hero: Superhero) {
        viewModel.setFav(hero)
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
    }

    private fun loadLocationsInMap(locations: List<SuperheroLocations>) {
        if (this::map.isInitialized){
            for (location in locations) {

                var countryName = " Cerca de "
                try {
                    val gcd = Geocoder(requireContext(), Locale.getDefault())
                    val addresses: List<Address>? = gcd.getFromLocation(location.latitude.toDouble(), location.longitude.toDouble(), 1)
                    if (addresses!!.isNotEmpty()) {
                        countryName += addresses[0].locality + ", " + addresses[0].countryName
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