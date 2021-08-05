package com.example.taptapmatching.matchingMain

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentDetailFilteringBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface  DetailFilteringFragementDelegate {
    fun didClose()
    fun applyFilterList(list: MutableSet<String>)
}

class DetailFilteringFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailFilteringBinding? = null
    private val binding get() = _binding!!

    var type: MatchingFilterRecycler.FilterType? = null

    var delegate: DetailFilteringFragementDelegate? = null
    var adapter = DetailFilteringRecycler.CustomAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFilteringBinding.inflate(inflater, container, false)
        val view = binding.root

        this.setupRecyclerView()
        this.setupListener()

        return view
    }

    fun setupRecyclerView() {
        var filterRecycler = DetailFilteringRecycler()
        val data: MutableList<String> = filterRecycler.loadData()
        adapter.listData = data
        filterRecycler.type = this.type

        binding.recyclerView2.adapter = adapter
        binding.recyclerView2.layoutManager = GridLayoutManager(activity, 3, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setupListener() {
        binding.filterDetailCloseButton.setOnClickListener {
            this.delegate?.didClose()
            this.onDestroyView()
        }

        binding.applyFilterButton.setOnClickListener {
            this.delegate?.applyFilterList(this.adapter.selectedListData)
        }
    }
}
