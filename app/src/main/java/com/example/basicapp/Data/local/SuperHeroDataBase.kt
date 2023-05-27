package com.example.basicapp.Data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.basicapp.Data.local.model.LocalSuperHeroLocation
import com.example.basicapp.Data.local.model.LocalSuperhero

class SuperHeroDataBase {
    @Database(entities = [LocalSuperhero::class, LocalSuperHeroLocation::class], version = 4)
    abstract class SuperheroDatabase : RoomDatabase() {
        abstract fun superheroDao(): SuperheroDAO
    }
}