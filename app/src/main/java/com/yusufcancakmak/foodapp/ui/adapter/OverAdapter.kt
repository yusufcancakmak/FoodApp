package com.yusufcancakmak.foodapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yusufcancakmak.foodapp.databinding.OverRowBinding
import com.yusufcancakmak.foodapp.ui.data.Over

class OverAdapter():RecyclerView.Adapter<OverAdapter.ViewHolder> (){

    lateinit var onOverItemClickListener:((Over)->Unit)
    private val diffUtil=object :DiffUtil.ItemCallback<Over>(){
        override fun areItemsTheSame(oldItem: Over, newItem: Over): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Over, newItem: Over): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffUtil)
    var overItemList:List<Over>
        get() = differ.currentList
        set(value){
            differ.submitList(value)
        }

    inner class ViewHolder(val binding: OverRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(OverRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentlist=overItemList[position]
        Glide.with(holder.itemView).load(currentlist.strMealThumb).into(holder.binding.overImg)


        holder.itemView.setOnClickListener {
            onOverItemClickListener.invoke(currentlist)
        }
    }

    override fun getItemCount()=overItemList.size
}