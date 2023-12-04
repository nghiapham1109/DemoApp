package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Product
import com.example.myapplication.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val productList = MutableLiveData<List<Product>>()

    fun fetchProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val products = repository.getProducts()
            withContext(Dispatchers.Main) {
                productList.value = products
            }
        }
    }
}