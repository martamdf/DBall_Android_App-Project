package com.example.basicapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.basicapp.data.local.model.LocalSuperhero

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
}


