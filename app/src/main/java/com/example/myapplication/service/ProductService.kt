package com.example.myapplication.service

import com.example.myapplication.response.ProductsResponse
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): ProductsResponse
}