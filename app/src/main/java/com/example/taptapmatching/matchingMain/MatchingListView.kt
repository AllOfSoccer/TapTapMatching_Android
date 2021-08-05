package com.example.taptapmatching

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.MatchingDetail.MatchingDetailActivity
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.example.taptapmatching.matchingMain.*
import com.example.taptapmatching.matchingMain.SelectSorting.OrderSortingFragment
import com.example.taptapmatching.matchingMain.SmallCalendar.MatchingCalendarRecycler
import com.google.android.material.tabs.TabLayout
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter


enum class MatchType {
    TEAM,
    MERCENARY
}

class MatchingListView : AppCompatActivity(), MatchingFilterRecyclerDelegate, DetailFilteringFragementDelegate, MatchingListRecyclerDelegate {

    var selectedDates: MutableSet<LocalDate> = mutableSetOf()
    val binding by lazy { ActivityMatchingListViewBinding.inflate(layoutInflater) }

    var calendarRecycler = MatchingCalendarRecycler()
    var filterRecycler = MatchingFilterRecycler()
    var matchingListRecycler = MatchingListRecycler()

    var currentMatchType: MatchType = MatchType.TEAM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)

        this.setupMonthListener()

        this.setupCalendarRecylcer()
        this.setupFilterRecycler()
        this.setupMatchingListRecylcer()

        this.setupButtonListener()
        this.setupTabLayout()

        this.setupFilteringOrder()
    }

    fun setupFilteringOrder() {
        binding.orderFilteringTextView.setOnClickListener {
            val orderSortingFragment = OrderSortingFragment()
            orderSortingFragment.show(supportFragmentManager, "OrderSortingFragment")
        }
    }

    fun setupTabLayout() {
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("경기 매칭"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("설정"))

        binding.mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("mainTabLayout", "onTabSelected ${tab?.text}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("mainTabLayout", "onTabUnselected ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("mainTabLayout", "onTabReselected ${tab?.text}")
            }
        })
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
        adapter2.delegate = this

        binding.filterRecyclerView.addItemDecoration(Test())
        binding.filterRecyclerView.adapter = adapter2
        binding.filterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setupMatchingListRecylcer() {
        val data: MutableList<MatchingData> = matchingListRecycler.loadData()
        var adapter = MatchingListRecycler.CustomAdapter()
        adapter.listData = data
        adapter.delegate = this

        binding.matchingListRecyclerView.adapter = adapter
        binding.matchingListRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun didSelectFilterType(type: MatchingFilterRecycler.FilterType) {
        // 이 타이밍에 하단 필터를 보여준다.
        Log.d("didSelectFilterType", "${type}")

        val detailFilteringFragment = DetailFilteringFragment()
        detailFilteringFragment.delegate = this
        detailFilteringFragment.type = type
        detailFilteringFragment.show(supportFragmentManager, "DetailFilteringFragment")
    }

    // Delegate 함수
    override fun didClose() {
        Log.d("MatchingListView", "didClose ${MatchingDataSource.shared.list}")
    }

    override fun applyFilterList(list: MutableSet<String>) {
        Log.d("MatchingListView", "applyList ${list}")
    }

    override fun didSelect(matching: MatchingData) {
        //내비게이션 Fragment 띄우기

        val matchingListIntent = Intent(this, MatchingDetailActivity::class.java)
        matchingListIntent.putExtra("data", matching as Serializable)

        this.startActivity(matchingListIntent)

        Log.d("matchingListView", "${matching}")
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
        }

    }

}






