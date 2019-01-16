package com.kohls.bootstrap.bootstrapapp.data.repository

import com.kohls.bootstrap.bootstrapapp.data.api.ProductService
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val productService: ProductService) {

    fun loadProducts(productID: String, urlParameters: Map<String, String>) =
        productService.listProducts(productID, urlParameters)

}