package com.printoverit.machinetestapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.printoverit.machinetestapp.dataclasses.Meme

@Database(entities = [Meme::class], version = 1)
abstract class TestDatabase : RoomDatabase(){

    abstract fun getMemeDao():TestDAO

    companion object{
        private var INSTANCE:TestDatabase? =null
        fun getDatabase(context: Context):TestDatabase{

            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(
                   context,
                    TestDatabase::class.java,
                    "memeDB"
                ).build()
            }
            return  INSTANCE!!
        }
    }

}