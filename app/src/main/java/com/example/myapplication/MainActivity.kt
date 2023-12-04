package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        productAdapter = ProductAdapter()
        binding.recyclerView.adapter = productAdapter

        viewModel.productList.observe(this) { products ->
            productAdapter.submitList(products)
        }

        viewModel.fetchProducts()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}