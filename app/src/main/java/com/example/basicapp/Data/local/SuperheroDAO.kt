package com.example.basicapp.Data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basicapp.Data.local.model.LocalSuperHeroLocation
import com.example.basicapp.Data.local.model.LocalSuperhero

@Dao
interface SuperheroDAO {
    @Query("SELECT * FROM superheroes")
    suspend fun getAll(): List<LocalSuperhero>

    @Query("SELECT * FROM superheroes WHERE id = :heroID")
    suspend fun getHero(heroID: String): LocalSuperhero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg hero: LocalSuperhero)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(vararg users: LocalSuperhero)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<LocalSuperhero>)

    @Delete
    suspend fun delete(user: LocalSuperhero)

    @Query("DELETE FROM superheroes")
    suspend fun deleteDataSuperheros()

    // LOCATIONS:
    @Query("SELECT * FROM locations WHERE id = :heroID")
    suspend fun getLocation(heroID: String): List<LocalSuperHeroLocation>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationsList(heroLocations: List<LocalSuperHeroLocation>)
}


