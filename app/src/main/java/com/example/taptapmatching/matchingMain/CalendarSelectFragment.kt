package com.example.taptapmatching.matchingMain

import android.os.Bundle
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

class CalendarSelectFragment : DialogFragment() {

    private var _binding: FragmentCalendarSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarSelectBinding.inflate(inflater, container, false)
        val view = binding.root
        this.setupFilterRecycler()
        return view
    }

    fun setupFilterRecycler() {
        val recycler = DialogCalendarRecycler()

        val data: MutableList<DialogCalendarRecycler.SmallDate> = recycler.loadData()
        var adapter = DialogCalendarRecycler.CustomAdapter()
        adapter.listData = data

        binding.dialogRecyclerView.adapter = adapter

        //val grid = GridLayoutManager(activity, 7, LinearLayoutManager.HORIZONTAL, false)
        val staggerd = StaggeredGridLayoutManager(7, LinearLayoutManager.HORIZONTAL)
        binding.dialogRecyclerView.layoutManager = staggerd
    }
}