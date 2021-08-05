package com.example.taptapmatching.matchingMain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import androidx.viewpager2.widget.ViewPager2
import com.example.taptapmatching.MatchingListView
import com.example.taptapmatching.databinding.DialogCalendarLayoutBinding
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding
import com.example.taptapmatching.matchingMain.SelectCalendar.DialogCalendarRecycler
import com.example.taptapmatching.matchingMain.SelectCalendar.recycler_calendar_fragment
import java.time.LocalDate

interface CalendarDialogDelegate {
    fun didSelect(dates: MutableSet<LocalDate>)
}

class CalendarSelectFragment() : DialogFragment(), CalendarDialogDelegate {

    var selectedDates: MutableSet<LocalDate> = mutableSetOf()

    private var _binding: FragmentCalendarSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarSelectBinding.inflate(inflater, container, false)
        val view = binding.root

        this.setupFilterRecycler()
        this.setupViewPager2()

        return view
    }

    override fun onResume() {
        super.onResume()

        binding.calendarViewPager.setCurrentItem(1, false)
    }

    fun setupFilterRecycler() {
        val recycler = DialogCalendarRecycler()

        val data: MutableList<DialogCalendarRecycler.SmallDate> = recycler.loadData()
        var adapter = DialogCalendarRecycler.CustomAdapter()
        adapter.delegate = this
        adapter.listData = data

        binding.dialogRecyclerView.adapter = adapter

        val grid = GridLayoutManager(activity, 7)
        binding.dialogRecyclerView.layoutManager = grid
    }

    fun setupViewPager2() {
        val recycler = ViewPager2Recycler()

        var adapter = ViewPager2Recycler.CustomAdapter(requireActivity())
        binding.calendarViewPager.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()

        val currentActivity = this.activity

        if (currentActivity is MatchingListView) {
            currentActivity.selectedDates = this.selectedDates
            Log.d("didSelect", "aaaa ${this.selectedDates}")
        }
    }

    override fun didSelect(dates: MutableSet<LocalDate>) {
        selectedDates = dates
        Log.d("didSelect", "dates ${dates}")
    }
}

class ViewPager2Recycler {

    class CustomAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            return recycler_calendar_fragment()
        }

    }

}

