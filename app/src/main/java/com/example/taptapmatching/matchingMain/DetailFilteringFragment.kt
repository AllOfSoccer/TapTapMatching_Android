package com.example.taptapmatching.matchingMain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentDetailFilteringBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailFilteringFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailFilteringBinding? = null
    private val binding get() = _binding!!

    var type: MatchingFilterRecycler.FilterType? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFilteringBinding.inflate(inflater, container, false)
        val view = binding.root
        Log.d("binding", "${view}")
        return view
    }
}
