package com.example.myapplication.presentation.bill

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.databinding.FragmentCreateBillBinding
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.database.model.Bill
import com.example.myapplication.data.database.model.BillDetail
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateBillFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateBillFragment : Fragment() {
    private lateinit var mContext: Context

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var db: AppDatabase
    private lateinit var bill: Bill
    private lateinit var billDetailList: List<BillDetail>
    lateinit var binding: FragmentCreateBillBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        db = AppDatabase.newInstance(mContext)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateBillBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dateOfBillEt.setText(getCurrentDate())
        binding.exportBillBtn.setOnClickListener {
            val customerName = binding.customerNameEt.text
            var customerPhone = binding.customerPhoneEt.text
            var bill = Bill(customerName.toString(), getCurrentDate(), "0")
        }

    }
    private fun getCurrentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return current.format(formatter)
    }
    private fun saveBillToDb() {
        db.runInTransaction {
            db.billDao().insertBill(bill)
            for (billDetail in billDetailList) {
                db.billDetailDao().insertBillDetail(billDetail)
            }
        }

    }

    private fun saveToFirebase() {
        val database = FirebaseDatabase.getInstance()
        val billRef = database.getReference("bills")
        val billDetailRef = database.getReference("bill_details")
        billRef.setValue(bill)
        billDetailRef.setValue(billDetailList)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CreateBillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateBillFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}