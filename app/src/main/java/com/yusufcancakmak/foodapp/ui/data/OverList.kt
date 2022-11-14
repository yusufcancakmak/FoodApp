package com.yusufcancakmak.foodapp.ui.data


import com.google.gson.annotations.SerializedName

data class OverList(
    @SerializedName("meals")
    val meals: List<Over>
)