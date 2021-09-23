package com.example.taptapmatching.MakeMatchingRoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.taptapmatching.databinding.FragmentBeforeMatchInfoBinding
import com.example.taptapmatching.databinding.FragmentPlaceSelectBinding

class BeforeMatchInfoFragment: DialogFragment() {
    private var _binding: FragmentBeforeMatchInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBeforeMatchInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }
}