package com.printoverit.machinetestapp.api

import com.printoverit.machinetestapp.dataclasses.Data
import com.printoverit.machinetestapp.dataclasses.MemeDataClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface TestAPI {

    @GET("get_memes")
    suspend fun getApiResponse(): Response<MemeDataClass>
}