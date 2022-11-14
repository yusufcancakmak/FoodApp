package com.yusufcancakmak.foodapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufcancakmak.foodapp.ui.data.Over
import com.yusufcancakmak.foodapp.ui.repository.CategoryFragmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryrepo:CategoryFragmentRepository) :ViewModel(){


    private val _getcategoryMutableLiveData= MutableStateFlow<List<Over>>(emptyList())

    val getcategorylivedata:StateFlow<List<Over>> =_getcategoryMutableLiveData


    fun getcategory(categoryName:String){
        viewModelScope.launch {
            categoryrepo.getCategory(categoryName).let { response ->

            if (response.isSuccessful){
                _getcategoryMutableLiveData.emit(response.body()!!.meals)
            }else{
                Log.d("testApp",response.message().toString())
            }

            }
        }
    }
}