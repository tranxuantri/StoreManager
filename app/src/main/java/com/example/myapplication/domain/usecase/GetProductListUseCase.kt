package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Product
import com.example.myapplication.domain.repository.ProductRepository
import java.io.IOException

internal class GetProductListUseCase(private val productRepository: ProductRepository) {
    sealed interface Result {
        data class Success(val data: List<Product>) : Result
        data class Error(val e: Throwable) : Result
    }

    suspend fun execute(): Result {
        // Due to API limitations search with custom phrase have to be performed to get albums

        return try {
            Result.Success(productRepository.getProductList())
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}