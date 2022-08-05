package com.example.myapplication.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.data.network.ProductApiService
import com.example.myapplication.data.network.model.ProductJson
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.domain.model.Product
import com.example.myapplication.utility.convert
import com.example.myapplication.utility.convertToList
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment(), Callback<Map<String,ProductJson>> {

    private var _binding: FragmentDashboardBinding? = null

    private var listProduct: List<Product> = ArrayList()
    private val productAdapter: ProductListAdapter = ProductListAdapter()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productListView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = productAdapter
        }

        val httpLogging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .addInterceptor(interceptor)
            .build()
        val gson =GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://storemanager-af1bb-default-rtdb.asia-southeast1.firebasedatabase.app")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ProductApiService::class.java)
        val call:Call<Map<String,ProductJson>> = retrofit.getProductList()
        call.enqueue(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResponse(call: Call<Map<String,ProductJson>>, response: Response<Map<String,ProductJson>>) {
        Timber.d(response.body().toString())
        listProduct = response.body()?.convertToList()?.convert() ?: ArrayList()
        productAdapter.products = listProduct
    }

    override fun onFailure(call: Call<Map<String,ProductJson>>, t: Throwable) {
        Timber.d("Error $t")
    }
}