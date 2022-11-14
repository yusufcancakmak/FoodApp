package com.yusufcancakmak.foodapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusufcancakmak.foodapp.databinding.FragmentCategoryBinding
import com.yusufcancakmak.foodapp.ui.adapter.CategoryFragmentAdapter
import com.yusufcancakmak.foodapp.ui.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class CategoryFragment : Fragment() {
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var categoryName:String
    private  val categoryMvvm:CategoryViewModel by viewModels()
    private lateinit var categoryFragmentAdapter: CategoryFragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryFragmentAdapter= CategoryFragmentAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         getDataByBundle()
        getCategoryInformation()
        setupCategoryRv()
    }

    private fun setupCategoryRv() {
        binding.categoryRv.apply {
            layoutManager=GridLayoutManager(context,2,RecyclerView.VERTICAL,false)
            adapter=categoryFragmentAdapter
        }
        lifecycleScope.launchWhenStarted {
            categoryMvvm.getcategory(categoryName)
            categoryMvvm.getcategorylivedata.collect{data->
            categoryFragmentAdapter.categorylist=data

            }
        }
    }

    private fun getCategoryInformation() {

    }

    private fun getDataByBundle() {
        val data=arguments
        if (data!=null){
            categoryName=data.getString("mealCategory").toString()
        }


    }

}