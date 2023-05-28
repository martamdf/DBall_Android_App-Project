package com.example.basicapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.example.basicapp.Data.Repository
import com.example.basicapp.Data.RepositoryImpl
import com.example.basicapp.Data.local.LocalDataSource
import com.example.basicapp.Data.remote.RemoteDataSource
import com.example.basicapp.UI.heroes.heroeslist.SuperHeroesViewModel
import com.example.basicapp.data.local.fakes.FakeLocalDataSource
import com.example.basicapp.data.remote.fakes.FakeRemoteDataSource
import com.example.basicapp.di.RepositoryModule
import com.example.basicapp.utils.generateHeroes
import com.example.basicapp.utils.getOrAwaitValue
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@UninstallModules(RepositoryModule::class)
class SuperHeroesViewModelHiltTest {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class FakeRepositoryModule {
        companion object {

            @Provides
            fun providesFakeRemoteDataSource(): FakeRemoteDataSource {
                return FakeRemoteDataSource()
            }
        }

        @Binds
        abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

        @Binds
        abstract fun bindsLocalDataSource(localDataSourceImpl: FakeLocalDataSource): LocalDataSource

        @Binds
        abstract fun bindsRemoteDataSource(remoteDataSourceImpl: FakeRemoteDataSource): RemoteDataSource
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule val hiltRule = HiltAndroidRule(this)

    // UUT o SUT
    private lateinit var viewModel: SuperHeroesViewModel

    @Inject
    lateinit var repository: Repository


    @Before
    fun setUp() {
        hiltRule.inject()
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = SuperHeroesViewModel(repository)
    }

    @Test
    fun `WHEN getHeros EXPECT successful response`() {
        // GIVEN
        val expected = generateHeroes(16)

        // WHEN
        val actual = viewModel.getHeroes()
        val actualLiveData = viewModel.heroes.getOrAwaitValue()

        // THEN
        Truth.assertThat(actualLiveData).containsExactlyElementsIn(expected)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}