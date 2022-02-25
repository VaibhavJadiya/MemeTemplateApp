package com.printoverit.machinetestapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memes")
data class TestEntities (
    val boxCount: Int,
    val height: Int,
    @PrimaryKey()
    val id: String,
    val name: String,
    val url: String,
    val width: Int

)