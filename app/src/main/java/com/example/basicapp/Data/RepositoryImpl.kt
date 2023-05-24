package com.example.basicapp.Data

import com.example.basicapp.Data.local.LocalDataSource
import com.example.basicapp.Data.mappers.LocalToPresentationMapper
import com.example.basicapp.Data.mappers.RemoteToLocalMapper
import com.example.basicapp.Data.remote.RemoteDataSourceImpl
import com.keepcoding.androidavanzado.data.remote.RemoteDataSource
import com.keepcoding.androidavanzado.ui.model.Superhero
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val localToPresentationMapper: LocalToPresentationMapper,
    private val remoteToLocalMapper: RemoteToLocalMapper,
): Repository{


    override suspend fun getHeroes(): List<Superhero> {
        if (localDataSource.getHeroes().isEmpty()) {
            val remoteSuperheros = remoteDataSource.getHeroes()
            localDataSource.insertHeroes(remoteToLocalMapper.mapGetHeroResponse(remoteSuperheros))
        }

        return localToPresentationMapper.mapLocalSuperheroes(localDataSource.getHeroes())
    }
}