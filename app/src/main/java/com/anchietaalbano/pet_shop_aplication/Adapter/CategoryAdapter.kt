package com.anchietaalbano.pet_shop_aplication.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anchietaalbano.pet_shop_aplication.Model.CategoryModel
import com.anchietaalbano.pet_shop_aplication.databinding.ViewholderCategoryBinding
import com.bumptech.glide.Glide

class CategoryAdapter(val items: MutableList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context: Context

    class Viewholder(val binding:ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item=items[position]
        holder.binding.titleCat.text=item.title

        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.picCat)
    }

    override fun getItemCount(): Int = items.size
}