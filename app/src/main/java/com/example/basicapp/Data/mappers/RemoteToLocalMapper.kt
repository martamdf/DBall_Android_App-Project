package com.example.basicapp.Data.mappers

import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.Data.remote.GetHeroesResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){
    fun mapGetHeroResponse(getHeroesResponse: List<GetHeroesResponse>): List<LocalSuperhero> {
        return getHeroesResponse.map { mapGetHeroResponse(it) }
    }

    fun mapGetHeroResponse(getHeroesResponse: GetHeroesResponse): LocalSuperhero {
        return LocalSuperhero(getHeroesResponse.id, getHeroesResponse.name, false)
    }
}