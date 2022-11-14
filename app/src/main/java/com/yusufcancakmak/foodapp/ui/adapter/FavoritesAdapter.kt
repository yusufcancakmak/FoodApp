package com.yusufcancakmak.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcancakmak.foodapp.databinding.FavoritsRowBinding
import com.yusufcancakmak.foodapp.ui.data.Meal

class FavoritesAdapter():RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FavoritsRowBinding):RecyclerView.ViewHolder(binding.root)

    private val diffutil=object :DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
           return newItem.idMeal==oldItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
           return newItem==oldItem
        }

    }
    private val differ= AsyncListDiffer(this,diffutil)
    var favoritesList:List<Meal>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FavoritsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentlist=favoritesList[position]

        holder.binding.favoritesName.text=currentlist.strMeal.toString()
        Glide.with(holder.itemView).load(currentlist.strMealThumb).into(holder.binding.favoritesImg)
    }

    override fun getItemCount()=favoritesList.size
}