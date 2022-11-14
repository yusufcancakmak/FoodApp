package com.yusufcancakmak.foodapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.yusufcancakmak.foodapp.R
import com.yusufcancakmak.foodapp.databinding.FragmentHomeBinding
import com.yusufcancakmak.foodapp.ui.activites.MealActivity
import com.yusufcancakmak.foodapp.ui.adapter.CategoriesAdapter
import com.yusufcancakmak.foodapp.ui.adapter.OverAdapter
import com.yusufcancakmak.foodapp.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeMvvm: HomeViewModel by viewModels()
    private lateinit var overAdapter:OverAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRandomMeal()
        getOverMeals()
        onOverItemClick()
        getCategoriesMeals()
        onCategoryItemClick()
    }

    private fun onCategoryItemClick() {
      categoriesAdapter.onCategoryItemClick={data->
          val fragment=CategoryFragment()
          val bundle =Bundle()
          bundle.putString("mealCategory",data.strCategory)
          fragment.arguments =bundle
          findNavController().navigate(R.id.action_homeFragment_to_categoryFragment,bundle)
      }
    }

    private fun onOverItemClick() {
       overAdapter.onOverItemClickListener = {data->
           val intent = Intent(context,MealActivity::class.java)
           intent.putExtra("mealId",data.idMeal)
           intent.putExtra("mealThumb",data.strMealThumb)
           intent.putExtra("mealTitle",data.strMeal)
           startActivity(intent)
       }
    }

    private fun getRandomMeal() {
        homeMvvm.getRandomMeal()
        homeMvvm.getRnadomLiveData.observe(viewLifecycleOwner) { data ->
            Glide.with(this).load(data.strMealThumb).into(binding.randomImage)
            binding.cardHome.setOnClickListener {
                val intent=Intent(this.context,MealActivity::class.java)
                intent.putExtra("mealId",data.idMeal)
                intent.putExtra("mealTitle",data.strMeal)
                intent.putExtra("mealThumb",data.strMealThumb)
                startActivity(intent)
            }
        }

    }
    private fun getOverMeals(){
        overAdapter= OverAdapter()
        binding.overRec.apply {
            adapter=overAdapter
            layoutManager=LinearLayoutManager(this.context,LinearLayoutManager.HORIZONTAL,false)
        }
        homeMvvm.getOverMeal()
        homeMvvm.getOverLiveData.observe(viewLifecycleOwner){data->
            overAdapter.overItemList=data
        }
    }
    private fun getCategoriesMeals(){
        categoriesAdapter= CategoriesAdapter()
        binding.categoriesRv.apply {
            adapter=categoriesAdapter
            layoutManager=GridLayoutManager(context,3,RecyclerView.VERTICAL,false)
        }
        homeMvvm.getCategorieHomeFragment()
        homeMvvm.getCategoriesLiveData.observe(viewLifecycleOwner){data->
            categoriesAdapter.categoriesList=data
            Log.d("testApp",data.toString())
        }

    }
}