package com.example.taptapmatching

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding
import com.example.taptapmatching.databinding.SmallcalendarRecyclerBinding
import com.example.taptapmatching.matchingMain.MatchingCalendarRecycler
import com.example.taptapmatching.matchingMain.MatchingFilterRecycler
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MatchingListView : AppCompatActivity() {

    val binding by lazy { ActivityMatchingListViewBinding.inflate(layoutInflater) }

    var calendarRecycler = MatchingCalendarRecycler()
    var filterRecycler = MatchingFilterRecycler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        this.setupCalendarRecylcer()
        this.setupFilterRecycler()
    }

    fun setupCalendarRecylcer() {
        val data: MutableList<MatchingCalendarRecycler.SmallDate> = calendarRecycler.loadData()
        var adapter = MatchingCalendarRecycler.CustomAdapter()
        adapter.listData = data
        binding.smallCalendarRecyclerView.adapter = adapter
        binding.smallCalendarRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setupFilterRecycler() {
        val data2: MutableList<MatchingFilterRecycler.FilterInfo> = filterRecycler.loadData()
        var adapter2 = MatchingFilterRecycler.CustomAdapter()
        adapter2.listData = data2
        binding.filterRecyclerView.adapter = adapter2
        binding.filterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}





