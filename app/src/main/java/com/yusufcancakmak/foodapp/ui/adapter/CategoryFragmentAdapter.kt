package com.yusufcancakmak.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcancakmak.foodapp.databinding.FavoritsRowBinding
import com.yusufcancakmak.foodapp.ui.data.Over

class CategoryFragmentAdapter ():RecyclerView.Adapter<CategoryFragmentAdapter.ViewHolder>(){
    private val diffUtil=object :DiffUtil.ItemCallback<Over>(){
        override fun areItemsTheSame(oldItem: Over, newItem: Over): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Over, newItem: Over): Boolean {
        return oldItem==newItem
        }

    }
    private val differ= AsyncListDiffer(this,diffUtil)
    var categorylist:List<Over>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    inner class ViewHolder(val binding:FavoritsRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(FavoritsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentlist=categorylist[position]
        Glide.with(holder.itemView).load(currentlist.strMealThumb).into(holder.binding.favoritesImg)
        holder.binding.favoritesName.text=currentlist.strMeal.toString()
    }

    override fun getItemCount()=categorylist.size
}