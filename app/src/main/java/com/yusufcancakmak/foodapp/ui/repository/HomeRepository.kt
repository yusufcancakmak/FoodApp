package com.yusufcancakmak.foodapp.ui.repository


import android.util.Log
import com.yusufcancakmak.foodapp.ui.data.CategoriesList
import com.yusufcancakmak.foodapp.ui.data.MealList
import com.yusufcancakmak.foodapp.ui.data.OverList
import com.yusufcancakmak.foodapp.ui.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api:MealApi){

    suspend fun getRandomMeal():Response<MealList>
    {
        val response =api.getRandomMeal()
        if (response.isSuccessful){
            Log.d("testapp","Success To Conneted to Random Meal")
            Log.d("testapp",response.code().toString())
        }else{
            Log.d("testapp","Failed To Conneted to Random Meal")
            Log.d("testapp",response.code().toString())

        }
        return response
    }


    suspend fun getOverMeal(categoryName:String):Response<OverList>{
        val response =api.getOverMeals(categoryName)
        if (response.isSuccessful){
            Log.d("testapp","Success To Conneted to Over Meal")
            Log.d("testapp",response.code().toString())
        }else{
            Log.d("testapp","Failed To Conneted to Over Meal")
            Log.d("testapp",response.code().toString())

        }
        return response
    }

    suspend fun getCategoriesMeal():Response<CategoriesList>{
        val response=api.getCategoriesHomeFragment()
        if (response.isSuccessful){
            Log.d("testapp","Success To Conneted to Categories Meal")
            Log.d("testapp",response.code().toString())
        }else{
            Log.d("testapp","Failed To Conneted to Categories Meal")
            Log.d("testapp",response.code().toString())

        }
        return response
        }
    }
