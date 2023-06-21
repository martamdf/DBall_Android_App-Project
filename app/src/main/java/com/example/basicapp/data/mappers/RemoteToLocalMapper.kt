package com.example.basicapp.data.mappers

import com.example.basicapp.data.local.model.LocalSuperHeroLocation
import com.example.basicapp.data.local.model.LocalSuperhero
import com.example.basicapp.data.remote.GetHeroLocationsResponse
import com.example.basicapp.data.remote.response.GetHeroesResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){
    fun mapGetHeroResponse(getHeroesResponse: List<GetHeroesResponse>): List<LocalSuperhero> {
        return getHeroesResponse.map { mapGetHeroResponse(it) }
    }

    private fun mapGetHeroResponse(getHeroesResponse: GetHeroesResponse): LocalSuperhero {
        return LocalSuperhero(getHeroesResponse.id, getHeroesResponse.name, getHeroesResponse.photo, getHeroesResponse.favorite)
    }

    fun mapGetHeroLocationResponse(getHeroLocationsResponse: List<GetHeroLocationsResponse>): List<LocalSuperHeroLocation> {
        return getHeroLocationsResponse.map { mapGetHeroLocationResponse(it) }
    }
    private fun mapGetHeroLocationResponse(getHeroLocationResponse: GetHeroLocationsResponse): LocalSuperHeroLocation {
        return LocalSuperHeroLocation(getHeroLocationResponse.id, getHeroLocationResponse.latitud, getHeroLocationResponse.longitud, getHeroLocationResponse.hero.id, getHeroLocationResponse.dateShow)
    }
}