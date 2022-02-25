package com.printoverit.machinetestapp.datasources

import com.printoverit.machinetestapp.room.TestDAO
import com.printoverit.machinetestapp.room.TestEntities

class LocalDataSource(private val testDAO: TestDAO) {

    suspend fun getOfflineMemes() :TestDAO{
        return  testDAO
    }
}