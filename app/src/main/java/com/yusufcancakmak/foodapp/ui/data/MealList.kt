package com.yusufcancakmak.foodapp.ui.data


import com.google.gson.annotations.SerializedName

data class MealList(
    @SerializedName("meals")
    val meals: List<Meal>
)