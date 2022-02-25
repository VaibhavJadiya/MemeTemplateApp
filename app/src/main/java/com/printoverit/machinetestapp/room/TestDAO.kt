package com.printoverit.machinetestapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.printoverit.machinetestapp.dataclasses.Meme

@Dao
interface TestDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemes(testEntities: List<Meme>)

    @Query("SELECT * FROM memes")
    suspend fun fetchMemes(): List<Meme>

    @Query("SELECT * FROM memes WHERE name LIKE :searchedQuery")
    suspend fun getSearchedData(searchedQuery:String): List<Meme>
}