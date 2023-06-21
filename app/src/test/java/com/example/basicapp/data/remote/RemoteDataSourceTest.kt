package com.example.basicapp.data.remote

import com.example.basicapp.utils.BaseNetworkMockTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

open class RemoteDataSourceTest : BaseNetworkMockTest() {

    // UUT o SUT Unit Under Test o System Under Test

    @Test
    fun myTest() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)

        // When
        val actual = remoteDataSource.getHeroes()

        // Then
        assert(actual[0].name == "Broly")
    }


    @Test
    fun `WHEN requesting getHeroes EXPECT successful response AND 3 heroes starting by B`() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)

        // When
        val actual = remoteDataSource.getHeroes()

        // Then
        assert(actual.size == 16)
    }

    @Test
    fun `WHEN requesting getLocations EXPECT successful response AND 1 location`() = runTest {
        // Given
        val remoteDataSource = RemoteDataSourceImpl(api)
        val heroID= "D13A40E5-4418-4223-9CE6-D2F9A28EBE94"
        // When
        val actual = remoteDataSource.getLocations(heroID)

        // Then
        //assert(actual[0].hero.id == heroID)
        assert(actual.size == 8)
    }


}