package com.example.taptapmatching.matchingMain

import android.content.Context
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
import com.example.taptapmatching.MatchingListView
import com.example.taptapmatching.R
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import java.time.LocalDate

interface CalendarDialogDelegate {
    fun didSelect(dates: MutableSet<LocalDate>)
}

class CalendarSelectFragment() : DialogFragment(), CalendarDialogDelegate {

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
        adapter.delegate = this
        adapter.listData = data

        binding.dialogRecyclerView.adapter = adapter

        val grid = GridLayoutManager(activity, 6)
        binding.dialogRecyclerView.layoutManager = grid
    }

    override fun onPause() {
        super.onPause()

        Log.d("didSelect", "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("didSelect", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Log.d("didSelect", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()

        val currentActivity = this.activity

        if (currentActivity is MatchingListView) {
            currentActivity.selectedDates = this.selectedDates
            Log.d("didSelect", "aaaa ${this.selectedDates}")
        }
    }

    var selectedDates: MutableSet<LocalDate> = mutableSetOf()

    override fun didSelect(dates: MutableSet<LocalDate>) {
        selectedDates = dates
        Log.d("didSelect", "dates ${dates}")
    }
}