package com.example.taptapmatching.matchingMain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentDetailFilteringBinding

class DetailFilteringFragment : DialogFragment() {

    private var _binding: FragmentDetailFilteringBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFilteringBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
}
