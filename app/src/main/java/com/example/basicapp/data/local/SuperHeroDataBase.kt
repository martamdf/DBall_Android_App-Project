package com.example.basicapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.basicapp.data.local.model.LocalSuperHeroLocation
import com.example.basicapp.data.local.model.LocalSuperhero

class SuperHeroDataBase {
    @Database(entities = [LocalSuperhero::class, LocalSuperHeroLocation::class], version = 4)
    abstract class SuperheroDatabase : RoomDatabase() {
        abstract fun superheroDao(): SuperheroDAO
    }
}