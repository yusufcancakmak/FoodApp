package com.yusufcancakmak.foodapp.ui.data


import com.google.gson.annotations.SerializedName

data class CategoriesList(
    @SerializedName("categories")
    val categories: List<Category>
)