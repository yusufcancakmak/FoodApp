package com.yusufcancakmak.foodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yusufcancakmak.foodapp.databinding.FragmentFavoritesBinding
import com.yusufcancakmak.foodapp.ui.adapter.FavoritesAdapter
import com.yusufcancakmak.foodapp.ui.viewmodel.MealActivityViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoritesAdapter: FavoritesAdapter
    private val mealmvvm :MealActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoritesAdapter= FavoritesAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFavoriteRv()

    }
    fun setupFavoriteRv(){
        binding.favoritesRv.apply {
            adapter=favoritesAdapter
            layoutManager=GridLayoutManager(this.context,2,RecyclerView.VERTICAL,false)

        }
        lifecycleScope.launchWhenStarted {
            mealmvvm.getSavedMeals().collect{ savedData ->
                favoritesAdapter.favoritesList=savedData
            }
        }
    }

}