package com.example.myapplication.presentation.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.network.ProductApiService
import com.example.myapplication.data.network.model.ProductJson
import com.example.myapplication.domain.model.Product
import com.example.myapplication.utility.convert
import com.example.myapplication.utility.convertToList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class ProductViewModel : ViewModel() {
    val product: MutableLiveData<List<Product>> by lazy {
        MutableLiveData<List<Product>>()
    }

    init {
        callApi()
    }

    fun getProductListResult(): LiveData<List<Product>> = product
    private fun callApi() {
        callbackProductList = CallBackListProduct(product)
        callApiGetListProduct()
    }

    private fun callApiGetListProduct() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://storemanager-af1bb-default-rtdb.asia-southeast1.firebasedatabase.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
        val call: Call<Map<String, ProductJson>> = retrofit.getProductList()
        call.enqueue(callbackProductList)
    }

    private var callbackProductList = CallBackListProduct(product)

    class CallBackListProduct(private val product: MutableLiveData<List<Product>>) :
        Callback<Map<String, ProductJson>> {
        override fun onResponse(
            call: Call<Map<String, ProductJson>>,
            response: Response<Map<String, ProductJson>>
        ) {
            Timber.d("onResponse")
            product.postValue(
                response.body()?.convertToList()?.convert()?.toMutableList() ?: ArrayList()
            )
        }

        override fun onFailure(call: Call<Map<String, ProductJson>>, t: Throwable) {
            Timber.d("Error $t")
        }
    }
}