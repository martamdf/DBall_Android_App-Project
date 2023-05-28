package com.example.basicapp.Data.remote

import com.squareup.moshi.Json

data class GetHeroLocationsResponse(
    @Json(name = "id") val id: String,
    @Json(name = "longitud") val longitud: String,
    @Json(name = "latitud") val latitud: String,
    @Json(name = "hero") val hero: GetHeroIdLocation,
    @Json(name = "dateShow") val dateShow: String
)

data class GetHeroIdLocation(
    @Json(name = "id") val id: String,
)
