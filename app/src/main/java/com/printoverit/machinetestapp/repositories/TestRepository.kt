package com.printoverit.machinetestapp.repositories

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.printoverit.machinetestapp.api.TestAPI
import com.printoverit.machinetestapp.dataclasses.Data
import com.printoverit.machinetestapp.dataclasses.Meme
import com.printoverit.machinetestapp.dataclasses.MemeDataClass
import com.printoverit.machinetestapp.datasources.LocalDataSource
import com.printoverit.machinetestapp.datasources.RemoteDataSource
import com.printoverit.machinetestapp.network.RetrofitInstance.Companion.getRetroInstance
import com.printoverit.machinetestapp.room.TestDAO
import com.printoverit.machinetestapp.room.TestDatabase
import com.printoverit.machinetestapp.room.TestEntities
import com.printoverit.machinetestapp.util.NetworkStatus.Companion.isNetworkAvailable
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response

class TestRepository(private val remoteDataSource: RemoteDataSource, private val testDatabase: TestDatabase, private val applicationContext: Context){

    private var memeMutable:MutableLiveData<MemeDataClass> =MutableLiveData<MemeDataClass>()
    val meme:LiveData<MemeDataClass>
    get() = memeMutable
    val remote=remoteDataSource
    suspend fun getMemes(){
        if (isNetworkAvailable(applicationContext)){
            val result=remote.getMemesApi()
            if (result.body()!=null){
                memeMutable.postValue(result.body())
               testDatabase.getMemeDao().insertMemes(result.body()!!.data.memes)
            }
        }
        else{
            val Offlinememes = testDatabase.getMemeDao().fetchMemes()
            val memeList=MemeDataClass(Data(Offlinememes),true)
            memeMutable.postValue(memeList)
        }

    }

    suspend fun getOfflineMemes(){
        val Offlinememes = testDatabase.getMemeDao().fetchMemes()
        if (Offlinememes.isEmpty()){
            Toast.makeText(applicationContext,"No Offline Data Available",Toast.LENGTH_SHORT).show()
        }else{
            val memeList=MemeDataClass(Data(Offlinememes),true)
            memeMutable.postValue(memeList)
        }

    }

    suspend fun getSearchedMemes(searched:String){
        val Offlinememes=testDatabase.getMemeDao().fetchMemes()
        memeMutable.postValue(MemeDataClass(Data(Offlinememes),true))
    }

}