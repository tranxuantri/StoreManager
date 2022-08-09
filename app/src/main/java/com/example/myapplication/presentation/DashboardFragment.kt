package com.example.myapplication.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentDashboardBinding
import com.example.myapplication.domain.model.Product
import com.example.myapplication.presentation.product.ProductViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private var listProduct: List<Product> = ArrayList()
    private val productAdapter: ProductListAdapter = ProductListAdapter()

    private val binding get() = _binding!!
    private val model: ProductViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            context.title = "Dashboard"
        }
    }
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

        model.getProductListResult().observe(viewLifecycleOwner) { result ->
            productAdapter.products = result
            productAdapter.notifyDataSetChanged()
            Timber.d("observe")
        }
    }
    override fun onResume() {
        super.onResume()
        activity?.title  = "Dashboard"
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}