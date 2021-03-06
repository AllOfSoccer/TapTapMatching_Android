package com.example.taptapmatching.matchingMain

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentDetailFilteringBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface  DetailFilteringFragementDelegate {
    fun didClose()
    fun applyFilterList(list: MutableSet<String>)
}

class DetailFilteringFragment(private var type: MatchingFilterRecycler.FilterType) : BottomSheetDialogFragment() {

    private var _binding: FragmentDetailFilteringBinding? = null
    private val binding get() = _binding!!

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
        val data: MutableList<String> = filterRecycler.loadData(this.type)
        adapter.listData = data

        binding.recyclerView2.adapter = adapter
        binding.recyclerView2.layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
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

    private class Test: RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.right = outRect.right.plus(30)
            outRect.top = outRect.top.plus(30)
        }

    }
}
