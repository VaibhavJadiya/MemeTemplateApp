package com.printoverit.machinetestapp.datasources

import com.printoverit.machinetestapp.api.TestAPI
import com.printoverit.machinetestapp.dataclasses.Data
import com.printoverit.machinetestapp.dataclasses.MemeDataClass
import retrofit2.Response

class RemoteDataSource(private val testApi:TestAPI) {

    suspend fun getMemesApi(): Response<MemeDataClass> {
        //get Data From Retrofit
        return testApi.getApiResponse()
    }
}