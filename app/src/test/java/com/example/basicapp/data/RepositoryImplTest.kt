package com.example.basicapp.data

import com.example.basicapp.data.local.LocalDataSourceImpl
import com.example.basicapp.data.mappers.LocalToPresentationMapper
import com.example.basicapp.data.mappers.PresentationToLocalMapper
import com.example.basicapp.data.mappers.RemoteToLocalMapper
import com.example.basicapp.data.remote.RemoteDataSourceImpl
import com.example.basicapp.utils.generateGetHeroesLocationsResponse
import com.example.basicapp.utils.generateGetHeroesResponse
import com.example.basicapp.utils.generateLocalSuperhero
import com.example.basicapp.utils.generateOneLocalSuperhero
import com.example.basicapp.utils.generateOnePresentationSuperhero
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {
    // SUT
    private lateinit var repositoryImpl: RepositoryImpl

    // Dependencies
    private lateinit var localDataSource: LocalDataSourceImpl
    private lateinit var remoteDataSource: RemoteDataSourceImpl
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var presentationToLocalMapper: PresentationToLocalMapper

    @Before
    fun setup() {
        localDataSource = mockk()
        remoteDataSource = mockk()
        remoteToLocalMapper = RemoteToLocalMapper()
        localToPresentationMapper = LocalToPresentationMapper()
        presentationToLocalMapper = PresentationToLocalMapper()
        repositoryImpl =
            RepositoryImpl(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper, presentationToLocalMapper)
    }

    // ESTE TEST NO VA BIEN PORQUE TENEMOS QUE CAMBIAR EL MOCK
    @Test
    fun `WHEN getHeroes EXPECT local empty return network`() = runTest {
        // GIVEN
        coEvery { localDataSource.getHeroes() } returns generateLocalSuperhero(16)
        coEvery { remoteDataSource.getHeroes() } returns generateGetHeroesResponse(16)
        coEvery { localDataSource.insertHeroes(any()) } just Runs

        // WHEN
        val actual = repositoryImpl.getHeroes()

        // THEN
        assert(actual.isNotEmpty())
    }

    @Test
    fun `WHEN getHero EXPECT returns a hero with a list of locations`() = runTest {
        // GIVEN
        coEvery { localDataSource.getHero("id") } returns generateOneLocalSuperhero()
        coEvery { remoteDataSource.getLocations("id") } returns generateGetHeroesLocationsResponse(2)

        // WHEN
        val actual = repositoryImpl.getHero("id")

        // THEN
        assert(actual.locations?.size ==2)
    }

    @Test
    fun `WHEN `() = runTest {
        // GIVEN
        val hero = generateOnePresentationSuperhero() // fav = false
        coEvery { localDataSource.insertHero(any()) } just Runs
        coEvery { remoteDataSource.setFav(hero.id) } just Runs

        // WHEN
        val actual = repositoryImpl.setFav(hero) // cambia a true

        // THEN
        assert(actual.favorite)
    }

    @Test
    fun WHEN () = runTest {
        // GIVEN
        val heroInicial = generateOnePresentationSuperhero() // fav = false

        coEvery { localDataSource.insertHero(any()) } just Runs
        coEvery { remoteDataSource.setFav("Paco Perez") } just Runs

        // WHEN
        val actual = repositoryImpl.setFav(heroInicial) // cambia a true


        // THEN
        assert(actual.favorite)
    }
}