package com.example.basicapp.di

import com.example.basicapp.Data.Repository
import com.example.basicapp.Data.RepositoryImpl
import com.example.basicapp.Data.local.LocalDataSource
import com.example.basicapp.Data.local.LocalDataSourceImpl
import com.example.basicapp.Data.remote.RemoteDataSource
import com.example.basicapp.Data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}