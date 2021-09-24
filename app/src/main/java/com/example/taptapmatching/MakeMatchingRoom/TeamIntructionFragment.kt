package com.example.taptapmatching.MakeMatchingRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taptapmatching.databinding.FragmentTeamIntruductionBinding

class TeamIntructionFragment: DialogFragment() {
    private var _binding: FragmentTeamIntruductionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamIntruductionBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}