package com.example.taptapmatching.matchingMain.SelectCalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentDetailFilteringBinding
import com.example.taptapmatching.databinding.FragmentRecyclerCalendarFragmentBinding

class recycler_calendar_fragment : Fragment() {

    private var _binding: FragmentRecyclerCalendarFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRecyclerCalendarFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

}