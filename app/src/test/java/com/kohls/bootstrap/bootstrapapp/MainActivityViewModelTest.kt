package com.kohls.bootstrap.bootstrapapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.kohls.bootstrap.bootstrapapp.data.api.ApiResponse
import com.kohls.bootstrap.bootstrapapp.data.api.ApiSuccessResponse
import com.kohls.bootstrap.bootstrapapp.data.model.Payload
import com.kohls.bootstrap.bootstrapapp.data.model.Product
import com.kohls.bootstrap.bootstrapapp.data.model.ProductList
import com.kohls.bootstrap.bootstrapapp.data.repository.ProductListRepository
import com.kohls.bootstrap.bootstrapapp.ui.main.MainViewModel
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest {

    var productListRepository: ProductListRepository = mock()
    lateinit var viewModel: MainViewModel
    var apiResponseObserver: Observer<ProductList?> = mock()
    var data = MutableLiveData<ApiResponse<ProductList>>()
    private var urlOptions: HashMap<String, String> = HashMap()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        whenever(
            productListRepository.loadProducts
                ("Gender:Mens Silhouette:Button-Down Shirts Category:Tops Department:Clothing", urlOptions)
        ).thenReturn(data)
        viewModel = MainViewModel(productListRepository)
        viewModel.productList.observeForever(apiResponseObserver)
    }

    @Test
    fun testSuccessResponse() {
        data.value = ApiSuccessResponse(ProductList(12, 12, 0, Payload(listOf())))
        verify(apiResponseObserver).onChanged(ProductList(12, 12, 0, Payload(listOf())))
    }

}