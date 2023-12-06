package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.adapter.ProductAdapter
import com.example.myapplication.repository.ProductRepository
import com.example.myapplication.viewModel.ProductViewModel
import com.example.myapplication.databinding.ListProductBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ListProductBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = ProductRepository()
        viewModel = ProductViewModel(repository)

        productAdapter = ProductAdapter(emptyList())
        binding.recyclerView.adapter = productAdapter

        viewModel.products.observe(this) { products ->
            productAdapter.submitList(products)
        }

        viewModel.colors.observe(this) { colors ->
            productAdapter.updateColors(colors)
        }
    }
}