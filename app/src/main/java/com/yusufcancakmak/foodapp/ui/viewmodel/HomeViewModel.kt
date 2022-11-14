package com.yusufcancakmak.foodapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcancakmak.foodapp.ui.data.Category
import com.yusufcancakmak.foodapp.ui.data.Meal
import com.yusufcancakmak.foodapp.ui.data.Over
import com.yusufcancakmak.foodapp.ui.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository):ViewModel() {


    private val _getRandomMealLiveDate=MutableLiveData<Meal>()

    val getRnadomLiveData:LiveData<Meal> = _getRandomMealLiveDate

        private var saveStateRandomMeal: Meal?=null
        fun getRandomMeal(){
            saveStateRandomMeal?.let { randomMeal ->
                _getRandomMealLiveDate.postValue(randomMeal)
            return
            }
            viewModelScope.launch {
               val response= repository.getRandomMeal()
                response.body()!!.meals.let {
                    _getRandomMealLiveDate.postValue(it[0])
                }
                saveStateRandomMeal =response.body()!!.meals[0]
                }
            }
        private val _getOverMealLiveData=MutableLiveData<List<Over>>()
         val getOverLiveData:LiveData<List<Over>> = _getOverMealLiveData
        fun getOverMeal(){
            viewModelScope.launch {
                val response=repository.getOverMeal("Seafood")
                if (response.isSuccessful){
                    response.body()?.meals?.let {
                    _getOverMealLiveData.postValue(it)
                    }

                }
            }
        }
    private val _getCategoriesMealLiveData=MutableLiveData<List<Category>>()
    val getCategoriesLiveData:LiveData<List<Category>> =_getCategoriesMealLiveData

    fun getCategorieHomeFragment(){
        viewModelScope.launch {
            val response=repository.getCategoriesMeal()
            if (response.isSuccessful){
                response.body()!!.categories.let {
                    _getCategoriesMealLiveData.postValue(it)
                }
            }
        }
    }
        }
