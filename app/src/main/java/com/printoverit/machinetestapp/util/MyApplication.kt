package com.printoverit.machinetestapp.util

import android.app.Application
import com.printoverit.machinetestapp.api.TestAPI
import com.printoverit.machinetestapp.datasources.RemoteDataSource
import com.printoverit.machinetestapp.network.RetrofitInstance
import com.printoverit.machinetestapp.repositories.TestRepository
import com.printoverit.machinetestapp.room.TestDatabase

class MyApplication : Application(){
    lateinit var testRepository: TestRepository

    override fun onCreate() {
        super.onCreate()
        val apiCall= RetrofitInstance.getRetroInstance().create(TestAPI::class.java)
        val remoteDataSource= RemoteDataSource(apiCall)
        val testDatabase= TestDatabase.getDatabase(applicationContext)
        testRepository=TestRepository(remoteDataSource,testDatabase,applicationContext)

    }
}