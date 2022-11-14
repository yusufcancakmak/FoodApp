package com.yusufcancakmak.foodapp.ui.repository

import android.util.Log
import com.yusufcancakmak.foodapp.ui.data.OverList
import com.yusufcancakmak.foodapp.ui.network.MealApi
import retrofit2.Response
import javax.inject.Inject

class CategoryFragmentRepository @Inject constructor(private val api:MealApi) {


    suspend fun getCategory(categoryName:String) :Response<OverList>{
        val response=api.getCategory(categoryName)
        if(response.isSuccessful){
            Log.d("testAapp","Success to connected to category")
            Log.d("tesApp",response.code().toString())
        }else{
            Log.d("testAapp","Failed to connected to category")
            Log.d("tesApp",response.code().toString())

        }
        return response
    }
}