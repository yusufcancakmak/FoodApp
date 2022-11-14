package com.yusufcancakmak.foodapp.ui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yusufcancakmak.foodapp.ui.data.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConvertar::class)
abstract class MealDatabase :RoomDatabase(){
    abstract fun mealDao():MealDao
}