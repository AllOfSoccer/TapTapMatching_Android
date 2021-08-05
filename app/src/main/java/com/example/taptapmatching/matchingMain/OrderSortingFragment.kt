package com.example.taptapmatching.matchingMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentDetailFilteringBinding
import com.example.taptapmatching.databinding.FragmentOrderSortingBinding

class OrderSortingFragment : DialogFragment() {

    private var _binding: FragmentOrderSortingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderSortingBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}