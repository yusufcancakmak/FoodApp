package com.yusufcancakmak.foodapp.ui.data


import com.google.gson.annotations.SerializedName

data class Over(
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String
)