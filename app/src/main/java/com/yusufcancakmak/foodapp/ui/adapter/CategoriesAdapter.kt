package com.yusufcancakmak.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcancakmak.foodapp.databinding.CategoriesRowBinding
import com.yusufcancakmak.foodapp.ui.data.Category

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.ViewHolder> (){

    lateinit var onCategoryItemClick :((Category)->Unit)
    inner class ViewHolder(val binding: CategoriesRowBinding):RecyclerView.ViewHolder(binding.root)


    private val diffUtil=object :DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return newItem.idCategory==oldItem.idCategory
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return newItem==oldItem
        }


    }
    private val differ=AsyncListDiffer(this,diffUtil)
    var categoriesList:List<Category>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CategoriesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentlist=categoriesList[position]
        holder.binding.categoriesName.text=currentlist.strCategory
        Glide.with(holder.itemView).load(currentlist.strCategoryThumb).into(holder.binding.imgCategories)

        holder.itemView.setOnClickListener {
            onCategoryItemClick.invoke(currentlist)
        }

    }

    override fun getItemCount()=categoriesList.size
}