package com.yusufcancakmak.foodapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcancakmak.foodapp.ui.data.Meal
import com.yusufcancakmak.foodapp.ui.repository.MealActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealActivityViewModel @Inject constructor(
    private val mealrepo:MealActivityRepository
):ViewModel (){
    private val _getMealInformationLiveData = MutableLiveData<Meal>()
    val getMealInformationLiveData :LiveData<Meal> = _getMealInformationLiveData


    fun getMealInformation(mealId:String){
        viewModelScope.launch {
            val response=mealrepo.getMealInformation(mealId)
            if (response.isSuccessful){
               _getMealInformationLiveData.postValue(response.body()!!.meals[0])

                }
            }
        }

    fun upsertMeal(meal :Meal)=viewModelScope.launch {
        mealrepo.upsertMeal(meal)
    }
    fun deleteMeal(meal: Meal)=viewModelScope.launch {
        mealrepo.deleteMeal(meal)
    }
    fun getSavedMeals()=mealrepo.getMealSaved
    }
