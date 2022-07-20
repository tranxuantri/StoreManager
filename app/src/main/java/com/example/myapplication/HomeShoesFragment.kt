package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentHomeShoesBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeShoesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeShoesFragment : Fragment() {
    private var binding: FragmentHomeShoesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeShoesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            buyShoesButton.setOnClickListener {
                findNavController().navigate(R.id.actionFromBuyShoes)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}