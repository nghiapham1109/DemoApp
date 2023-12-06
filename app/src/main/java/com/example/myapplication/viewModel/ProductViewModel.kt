package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Color
import com.example.myapplication.model.Product
import com.example.myapplication.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _colors = MutableLiveData<List<Color>>()
    val colors: LiveData<List<Color>> get() = _colors

    private val _colorMappings = MutableLiveData<Map<Int, String>>()
    val colorMappings: LiveData<Map<Int, String>> get() = _colorMappings

    init {
        fetchProducts()
        fetchColors()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            val products = repository.getProducts()
            _products.value = products
            createColorMappings(products)
        }
    }

    private fun fetchColors() {
        viewModelScope.launch {
            val colors = repository.getColors()
            _colors.value = colors
        }
    }

    private fun createColorMappings(products: List<Product>) {
        val colorMappings = mutableMapOf<Int, String>()
        for (product in products) {
            val colorName = _colors.value?.firstOrNull { it.id == product.color }?.name ?: "Null"
            colorMappings[product.id] = colorName
        }
        _colorMappings.value = colorMappings
    }
}