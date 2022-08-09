package com.example.myapplication.presentation.cart

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.databinding.FragmentShopppingCartBinding
import com.example.myapplication.utility.convert

/**
 * A simple [Fragment] subclass.
 * Use the [ShoppingCartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoppingCartFragment : Fragment() {
    private var appDatabase = context?.let { AppDatabase.newInstance(it) }
    private var _binding: FragmentShopppingCartBinding? = null
    private val binding get() = _binding!!
    private val shoppingCartAdapter: ShoppingCartAdapter = ShoppingCartAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            context.title = "Shopping Cart"
        }
        appDatabase = AppDatabase.newInstance(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShopppingCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productListView.apply {
            adapter = shoppingCartAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        shoppingCartAdapter.submitList(appDatabase?.productDao()?.getAll()?.convert())
    }

    override fun onResume() {
        super.onResume()
        activity?.title  = "Shopping Cart"
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ShoppingCartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ShoppingCartFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}