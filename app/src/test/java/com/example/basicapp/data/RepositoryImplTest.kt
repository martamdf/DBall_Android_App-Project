package com.example.basicapp.data

import com.example.basicapp.Data.RepositoryImpl
import com.example.basicapp.Data.mappers.LocalToPresentationMapper
import com.example.basicapp.Data.mappers.PresentationToLocalMapper
import com.example.basicapp.Data.mappers.RemoteToLocalMapper
import com.example.basicapp.Data.remote.RemoteDataSourceImpl
import com.example.basicapp.data.local.fakes.FakeLocalDataSource
import com.example.basicapp.utils.generateGetHeroesResponse
import com.example.basicapp.utils.generateLocalSuperhero
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
    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: RemoteDataSourceImpl
    private lateinit var remoteToLocalMapper: RemoteToLocalMapper
    private lateinit var localToPresentationMapper: LocalToPresentationMapper
    private lateinit var presentationToLocalMapper: PresentationToLocalMapper

    @Before
    fun setup() {
        localDataSource = mockk()//FakeLocalDataSource()
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
    fun `WHEN getHeros EXPECT successful network response first call and successful local response next call`() {
        // GIVEN

        // WHEN

        // THEN
    }
}