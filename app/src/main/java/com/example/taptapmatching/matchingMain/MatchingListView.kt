package com.example.taptapmatching

import android.R
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.example.taptapmatching.matchingMain.CalendarSelectFragment
import com.example.taptapmatching.matchingMain.MatchingCalendarRecycler
import com.example.taptapmatching.matchingMain.MatchingFilterRecycler
import java.time.format.DateTimeFormatter


enum class MatchType {
    TEAM,
    MERCENARY
}

class MatchingListView : AppCompatActivity() {

    val binding by lazy { ActivityMatchingListViewBinding.inflate(layoutInflater) }

    var calendarRecycler = MatchingCalendarRecycler()
    var filterRecycler = MatchingFilterRecycler()

    var currentMatchType: MatchType = MatchType.TEAM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)

        this.setupMonthListener()

        this.setupCalendarRecylcer()
        this.setupFilterRecycler()

        this.setupButtonListener()
    }

    fun setupMonthListener() {
        binding.month.setOnClickListener {
            val calendarSelectFragment = CalendarSelectFragment()
            calendarSelectFragment.show(supportFragmentManager, "CalendarSelectFragment")
        }
    }

    fun setupButtonListener() {
        binding.teamButton.setOnClickListener {
            this.currentMatchType = MatchType.TEAM
            it.isSelected = !it.isSelected
        }

        binding.mercenaryButton.setOnClickListener {
            this.currentMatchType = MatchType.MERCENARY
            it.isSelected = !it.isSelected
        }
    }

    fun setupCalendarRecylcer() {
        val data: MutableList<MatchingCalendarRecycler.SmallDate> = calendarRecycler.loadData()
        var adapter = MatchingCalendarRecycler.CustomAdapter()
        adapter.listData = data
        binding.smallCalendarRecyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.smallCalendarRecyclerView.layoutManager = layoutManager

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val totalItemCount = layoutManager.getItemCount()
                val firstVisible = layoutManager.findFirstVisibleItemPosition()

                val month = data[firstVisible].date.format(DateTimeFormatter.ofPattern("M월"))
                binding.month.text = month
            }
        }
        binding.smallCalendarRecyclerView.addOnScrollListener(scrollListener)

        //초기 월 세팅.
        val month = data[0].date.format(DateTimeFormatter.ofPattern("M월"))
        binding.month.text = month
    }

    fun setupFilterRecycler() {
        val data2: MutableList<MatchingFilterRecycler.FilterInfo> = filterRecycler.loadData()
        var adapter2 = MatchingFilterRecycler.CustomAdapter()
        adapter2.listData = data2
        binding.filterRecyclerView.adapter = adapter2
        binding.filterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}






