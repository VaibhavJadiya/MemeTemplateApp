package com.printoverit.machinetestapp.dataclasses


import com.google.gson.annotations.SerializedName

data class MemeDataClass(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
)