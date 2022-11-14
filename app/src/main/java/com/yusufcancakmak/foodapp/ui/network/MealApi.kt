package com.yusufcancakmak.foodapp.ui.network

import com.yusufcancakmak.foodapp.ui.data.CategoriesList
import com.yusufcancakmak.foodapp.ui.data.MealList
import com.yusufcancakmak.foodapp.ui.data.OverList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal():Response<MealList>


    @GET("filter.php")
    suspend fun getOverMeals(@Query("c")categoryName:String):Response<OverList>


    @GET("categories.php")
    suspend fun getCategoriesHomeFragment():Response<CategoriesList>

    @GET("lookup.php")
    suspend fun getMealInformation(@Query("i")mealId:String):Response<MealList>

    @GET("filter.php")
    suspend fun getCategory(@Query("c")categoryName:String):Response<OverList>
}