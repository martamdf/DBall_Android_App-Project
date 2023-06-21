package com.example.basicapp.utils

import org.junit.After
import org.junit.Before


open class DaoTest {

    //lateinit var dao: SuperheroDAO


    @Before
    fun setup() {

//        var prueba = Room.inMemoryDatabaseBuilder(context, TestDatabase::class.java).build()
//        dao = Room.databaseBuilder(
//          context,
//            SuperHeroDataBase.SuperheroDatabase::class.java, "noname"
//        ).fallbackToDestructiveMigration().build().superheroDao()

    }

    @After
    fun tearDown() {

    }
/*
    override suspend fun getAll(): List<LocalSuperhero> {
        TODO("Not yet implemented")
    }

    override suspend fun getHero(heroID: String): LocalSuperhero {
        TODO("Not yet implemented")
    }

    override suspend fun insert(vararg hero: LocalSuperhero) {
        TODO("Not yet implemented")
    }

    override suspend fun insertHero(vararg users: LocalSuperhero) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAllList(users: List<LocalSuperhero>) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(user: LocalSuperhero) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDataSuperheros() {
        TODO("Not yet implemented")
    }*/

//    override suspend fun getLocation(heroID: String): List<LocalSuperHeroLocation> {
//
//    }
//
//    override suspend fun insertLocationsList(heroLocations: List<LocalSuperHeroLocation>) {
//
//    }
}