package com.kohls.bootstrap.bootstrapapp.data.api

import android.arch.lifecycle.LiveData
import com.kohls.bootstrap.bootstrapapp.data.model.ProductList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ProductService {

    @GET("/api/browse/v1/browse/catalog/{productID}")
    fun listProducts(@Path("productID") productID : String, @QueryMap options : Map<String, String> ) : LiveData<ApiResponse<ProductList>>
}