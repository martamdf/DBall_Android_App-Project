package com.example.basicapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.basicapp.Data.Repository
import com.example.basicapp.UI.heroes.herodetail.SuperHeroViewModel
import com.example.basicapp.utils.generateOneSuperhero
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

class SuperHeroViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: SuperHeroViewModel

    // Mocks
    private lateinit var repository: Repository
    // Fakes

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
        val actual = viewModel.getHero(id)
        val actualLiveData = viewModel.hero.getOrAwaitValue()

        // THEN
        assert(actualLiveData.locations?.isEmpty() ?: false)

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}