package com.example.basicapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.basicapp.data.Repository
import com.example.basicapp.ui.heroes.herodetail.SuperHeroViewModel
import com.example.basicapp.utils.generateOneSuperhero
import com.example.basicapp.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SuperHeroViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: SuperHeroViewModel

    private lateinit var repository: Repository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = SuperHeroViewModel(repository)
    }

    @Test
    fun `WHEN getHero EXPECT successful response`()  {
        // GIVEN
        val id = "testingID"
        coEvery { repository.getHero(id) } returns generateOneSuperhero()

        // WHEN
        viewModel.getHero(id)
        val actualLiveData = viewModel.hero.getOrAwaitValue()

        // THEN
        assert(actualLiveData.locations?.isEmpty() ?: false)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}