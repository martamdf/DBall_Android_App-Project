package com.example.basicapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.basicapp.Data.Repository
import com.example.basicapp.UI.heroes.heroeslist.SuperHeroesViewModel
import com.example.basicapp.utils.generateHeroes
import com.example.basicapp.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeoutException

class SuperHeroesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: SuperHeroesViewModel

    // Mocks
    private lateinit var repository: Repository
    // Fakes

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = SuperHeroesViewModel(repository)
    }

    @Test
    fun `WHEN getHeros EXPECT successful response`()  {
        // GIVEN
        val expected = generateHeroes(16)
        coEvery { repository.getHeroes() } returns expected

        // WHEN
        val actual = viewModel.getHeroes()
        val actualLiveData = viewModel.heroes.getOrAwaitValue()

        // THEN
        assert(actualLiveData.isNotEmpty())

    }

    // Revisar excepción
    @Test(expected = TimeoutException::class)
    fun `WHEN getHeros EXPECT error response`() {
        // GIVEN
        coEvery { repository.getHeroes() } throws TimeoutException()

        // WHEN
        val actual = viewModel.getHeroes()
        val actualLiveData = viewModel.heroes.getOrAwaitValue()

        // THEN
        assert(actualLiveData.isEmpty())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}