package com.yusufcancakmak.foodapp.ui.activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.yusufcancakmak.foodapp.databinding.ActivityMealBinding
import com.yusufcancakmak.foodapp.ui.data.Meal
import com.yusufcancakmak.foodapp.ui.viewmodel.MealActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding

    private lateinit var mealId    :String
    private lateinit var mealThumb :String
    private lateinit var mealTitle :String
    private lateinit var youtube   :String
    private val mealmvvm:MealActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getMealInformation()
        mealmvvm.getMealInformation(mealId)
        observeGetMealInformaton()

        binding.btnFav.setOnClickListener {
            saveMeal?.let { meal ->
                   mealmvvm.upsertMeal(meal)
            }
            }
        }

    private var saveMeal:Meal ? = null
    private fun observeGetMealInformaton() {
        mealmvvm.getMealInformationLiveData.observe(this, Observer{ data ->
            saveMeal=data
            binding.categories.text="Category :"+data.strCategory
            binding.location.text="Area: "+data.strArea
            binding.detailsInstractions.text=data.strInstructions
            youtube =data.strYoutube
            binding.imgYoutube.setOnClickListener {
                val intent=Intent(Intent.ACTION_VIEW, Uri.parse(youtube))
                startActivity(intent)
            }
        })
    }


    private fun getMealInformation() {
        val intent=intent
        mealId    =intent.getStringExtra("mealId").toString()
        mealThumb =intent.getStringExtra("mealThumb").toString()
        mealTitle =intent.getStringExtra("mealTitle").toString()


        Glide.with(applicationContext).load(mealThumb).into(binding.mealImage)
        binding.collapsing.title=mealTitle
        Log.d("testApp",mealId)
    }
}