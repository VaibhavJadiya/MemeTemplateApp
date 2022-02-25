package com.printoverit.machinetestapp.dataclasses


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "memes")
@Parcelize
data class Meme(
    val boxCount: Int,
    val height: Int,
    @PrimaryKey()
    val id: String,
    val name: String,
    val url: String,
    val width: Int
):Parcelable