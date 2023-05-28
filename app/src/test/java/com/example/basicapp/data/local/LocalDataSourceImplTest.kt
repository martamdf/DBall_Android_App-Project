package com.example.basicapp.data.local

import com.example.basicapp.Data.remote.RemoteDataSourceImpl
import com.example.basicapp.utils.DaoTest
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class LocalDataSourceImplTest : DaoTest() {

    @Test
    fun myTest() = runTest {
        // Given
        val localDataSourceImplTest = DaoTest()

        // When
        val actual = localDataSourceImplTest.getHero("jj") //remoteDataSource.getHeroes()

        // Then

    }

}