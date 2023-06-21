package com.example.basicapp.di

import android.content.Context
import androidx.room.Room
import com.example.basicapp.data.local.SuperHeroDataBase
import com.example.basicapp.data.local.SuperheroDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesSuperheroDatabase(@ApplicationContext context: Context): SuperHeroDataBase.SuperheroDatabase {
        val db = Room.databaseBuilder(
            context,
            SuperHeroDataBase.SuperheroDatabase::class.java, "superhero-db"
        ).fallbackToDestructiveMigration().build()
        return db
    }

    @Provides
    fun providesDao(db: SuperHeroDataBase.SuperheroDatabase): SuperheroDAO {
        val dao = db.superheroDao()
        return dao
    }

}