package com.example.myapplication.presentation.product

import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Product
import com.example.myapplication.domain.usecase.GetProductListUseCase
import com.example.myapplication.presentation.viewmodel.BaseAction
import com.example.myapplication.presentation.viewmodel.BaseViewModel
import com.example.myapplication.presentation.viewmodel.BaseViewState
import com.example.myapplication.presentation.product.ProductViewModel.ViewState
import com.example.myapplication.presentation.product.ProductViewModel.Action
import com.example.myapplication.presentation.product.ProductViewModel.Action.ProductListLoadSuccess
import com.example.myapplication.presentation.product.ProductViewModel.Action.ProductListLoadFailure

import kotlinx.coroutines.launch

internal class ProductViewModel(private val getProductListUseCase: GetProductListUseCase) :
    BaseViewModel<ViewState, Action>(ViewState()) {

    override fun onLoadData() {
        getListProduct()
    }

    private fun getListProduct() {
        viewModelScope.launch {
            getProductListUseCase.execute().also {
                when {
                    it is GetProductListUseCase.Result.Success -> sendAction(
                        ProductListLoadSuccess(
                            it.data
                        )
                    )
                    it is GetProductListUseCase.Result.Error -> sendAction(ProductListLoadFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is ProductListLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            products = viewAction.products
        )
        is ProductListLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            products = ArrayList()
        )
    }

    internal data
    class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val products: List<Product> = ArrayList()
    ) : BaseViewState

    internal sealed interface Action : BaseAction {
        class ProductListLoadSuccess(val products: List<Product>) : Action
        object ProductListLoadFailure : Action
    }
}