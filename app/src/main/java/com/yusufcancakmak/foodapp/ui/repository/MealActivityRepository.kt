package com.yusufcancakmak.foodapp.ui.repository

import android.util.Log
import com.yusufcancakmak.foodapp.ui.data.Meal
import com.yusufcancakmak.foodapp.ui.data.MealList
import com.yusufcancakmak.foodapp.ui.database.MealDatabase
import com.yusufcancakmak.foodapp.ui.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class MealActivityRepository @Inject constructor(private val api:MealApi,private val db :MealDatabase){


   private val database=db.mealDao()


suspend fun getMealInformation(mealId:String):Response<MealList>{
    val response=api.getMealInformation(mealId)
    if (response.isSuccessful){
        Log.d("testApp","Success To Connected to MealInformation")
        Log.d("testApp",response.code().toString())
    }else{
        Log.d("testApp","Failed To connected to MealInformation")
        Log.d("testApp",response.code().toString())

    }
    return response
}


    suspend fun upsertMeal(meal: Meal){
        database.upsert(meal)
    }
    suspend fun deleteMeal(meal: Meal){
        database.deleteMeal(meal)
    }
    val getMealSaved =database.getSavedMeal()
}