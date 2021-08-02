package com.example.taptapmatching

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
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

    var currentMatchType: MatchType = MatchType.TEAM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)

        this.setupCalendarRecylcer()
        this.setupFilterRecycler()

        this.setupButtonListener()
    }

    fun setupButtonListener() {
        binding.teamButton.setOnClickListener {
            this.currentMatchType = MatchType.TEAM
            it.isSelected = !it.isSelected

            Log.d("ddd","teamButtonTapped")

            binding.matchHighlightView.setBackgroundColor(Color.BLUE)
        }

        binding.mercenaryButton.setOnClickListener {
            this.currentMatchType = MatchType.MERCENARY
            it.isSelected = !it.isSelected

            Log.d("ddd","mercenaryButtonTapped")

            binding.matchHighlightView.setBackgroundColor(Color.BLUE)
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

                Log.d("onScrollStateChanged", "첫번째 보이는 position 값: ${firstVisible}")
            }
        }
        binding.smallCalendarRecyclerView.addOnScrollListener(scrollListener)
    }

    fun setupFilterRecycler() {
        val data2: MutableList<MatchingFilterRecycler.FilterInfo> = filterRecycler.loadData()
        var adapter2 = MatchingFilterRecycler.CustomAdapter()
        adapter2.listData = data2
        binding.filterRecyclerView.adapter = adapter2
        binding.filterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}

enum class MatchType {
    TEAM,
    MERCENARY
}






