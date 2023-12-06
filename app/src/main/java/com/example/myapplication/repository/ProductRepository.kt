package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.model.Color
import com.example.myapplication.model.Product
import com.example.myapplication.service.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hiring-test.stag.tekoapis.net/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val productService: ProductService = retrofit.create(ProductService::class.java)

    suspend fun getProducts(): ArrayList<Product> {
        try {
            val productsResponse = productService.getProducts()
            Log.d("ProductRepository", "API call successful. Response: $productsResponse")
            return productsResponse
        } catch (e: Exception) {
            Log.e("ProductRepository", "API call failed: ${e.message}", e)
            throw e
        }
    }

    suspend fun getColors(): ArrayList<Color> {
        try {
            val colorResponse = productService.getColor()
            Log.d("ColorRepository", "API call successful. Response: $colorResponse")
            return colorResponse
        } catch (e: Exception) {
            Log.e("ColorRepository", "API call failed: ${e.message}", e)
            throw e
        }
    }
}