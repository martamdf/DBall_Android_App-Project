package com.example.basicapp.Data.mappers

import com.example.basicapp.Data.local.model.LocalSuperhero
import com.example.basicapp.Data.remote.GetHeroesResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor(){
    fun mapGetHeroResponse(getHerosResponses: List<GetHeroesResponse>): List<LocalSuperhero> {
        return getHerosResponses.map { mapGetHeroResponse(it) }
    }

    fun mapGetHeroResponse(getHerosResponse: GetHeroesResponse): LocalSuperhero {
        return LocalSuperhero(getHerosResponse.id, getHerosResponse.name, false)
    }
}