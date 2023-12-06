package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.model.Product
import com.example.myapplication.databinding.ProductItemBinding
import com.example.myapplication.model.Color
import com.example.myapplication.model.MappingColor

class ProductAdapter(private val colorMappings: List<MappingColor>) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    private var colors: List<Color> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        val colorMapping = colorMappings.firstOrNull { it.id == product.id }
        val color = colors.firstOrNull { it.id == colorMapping?.id }

        holder.bind(product, color)
    }

    class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, color: Color?) {
            binding.product = product
            binding.color = color

            Glide.with(binding.root)
                .load(product.image)
                .centerCrop()
                .into(binding.productImage)

            binding.executePendingBindings()
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    fun updateColors(newColors: List<Color>) {
        colors = newColors
        notifyDataSetChanged()
    }
}