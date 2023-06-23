package com.example.basicapp.data

import com.example.basicapp.data.local.LocalDataSource
import com.example.basicapp.data.local.fakes.FakeLocalDataSource
import com.example.basicapp.data.mappers.LocalToPresentationMapper
import com.example.basicapp.data.mappers.PresentationToLocalMapper
import com.example.basicapp.data.mappers.RemoteToLocalMapper
import com.example.basicapp.data.remote.RemoteDataSource
import com.example.basicapp.data.remote.fakes.FakeRemoteDataSource
import com.example.basicapp.utils.generateGetHeroesResponse
import com.example.basicapp.utils.generateLocalSuperhero
import com.example.basicapp.utils.generateOnePresentationSuperhero
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {
    // SUT
    private lateinit var repositoryImpl: RepositoryImpl

    // Dependencies
    private lateinit var localDataSource: LocalDataSource
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var presentationToLocalMapper: PresentationToLocalMapper

    @Before
    fun setup() {
        remoteToLocalMapper = RemoteToLocalMapper()
        localToPresentationMapper = LocalToPresentationMapper()
        presentationToLocalMapper = PresentationToLocalMapper()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN getHeroes EXPECT local empty return network`() = runTest {
        // GIVEN
        localDataSource = mockk()
        remoteDataSource = mockk()
        repositoryImpl =
            RepositoryImpl(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper, presentationToLocalMapper)

        coEvery { localDataSource.getHeroes() } returns generateLocalSuperhero(16)
        coEvery { remoteDataSource.getHeroes() } returns generateGetHeroesResponse(16)
        coEvery { localDataSource.insertHeroes(any()) } just Runs

        // WHEN
        val actual = repositoryImpl.getHeroes()

        // THEN
        assert(actual.isNotEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN getHero EXPECT returns a hero`() = runTest {
        // GIVEN
        localDataSource = FakeLocalDataSource()
        remoteDataSource = FakeRemoteDataSource()
        repositoryImpl =
            RepositoryImpl(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper, presentationToLocalMapper)
        // WHEN
        val actual = repositoryImpl.getHero("id")

        // THEN
        assert(!actual.favorite)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `WHEN setFav the property of the hero switch from false to true`() = runTest {
        // GIVEN
        localDataSource = mockk()
        remoteDataSource = mockk()
        repositoryImpl =
            RepositoryImpl(localDataSource, remoteDataSource, localToPresentationMapper, remoteToLocalMapper, presentationToLocalMapper)

        val hero = generateOnePresentationSuperhero() // fav = false
        coEvery { localDataSource.insertHero(any()) } just Runs
        coEvery { remoteDataSource.setFav(hero.id) } just Runs

        // WHEN
        val actual = repositoryImpl.setFav(hero) // changes to true

        // THEN
        assert(actual.favorite)
    }
}