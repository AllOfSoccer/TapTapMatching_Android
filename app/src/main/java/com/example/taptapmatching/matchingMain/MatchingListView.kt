package com.example.taptapmatching

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.MakeMatchingRoom.MakeSecondTeamMatchingRoomActivity
import com.example.taptapmatching.MakeMatchingRoom.MakeTeamMatchingRoomActivity
import com.example.taptapmatching.MatchingDetail.MatchingDetailActivity
import com.example.taptapmatching.Setting.SettingActivity
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.example.taptapmatching.matchingMain.*
import com.example.taptapmatching.matchingMain.SelectSorting.OrderSortingFragment
import com.example.taptapmatching.matchingMain.SmallCalendar.MatchingCalendarRecycler
import com.example.taptapmatching.matchingMain.SmallCalendar.MatchingCalendarRecyclerDelegate
import com.google.android.material.tabs.TabLayout
import java.io.Serializable
import java.time.LocalDate
import java.time.format.DateTimeFormatter


enum class MatchType {
    TEAM,
    MERCENARY
}

class MatchingListView : AppCompatActivity(), MatchingFilterRecyclerDelegate, DetailFilteringFragementDelegate, MatchingListRecyclerDelegate, MatchingCalendarRecyclerDelegate {

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
        this.setupAddMatchingButtons()
    }

    fun setupAddMatchingButtons() {
        binding.makeTeamButton.isVisible = false
        binding.makeMercenaryButton.isVisible = false

        binding.addMatchingButton.setOnClickListener {
            binding.makeTeamButton.isVisible = !binding.makeTeamButton.isVisible
            binding.makeMercenaryButton.isVisible = !binding.makeMercenaryButton.isVisible
        }

        binding.makeTeamButton.setOnClickListener {
            val matchingListIntent = Intent(this, MakeTeamMatchingRoomActivity::class.java)
            val matchingType = MatchType.TEAM
            matchingListIntent.putExtra("data", matchingType as Serializable)

            this.startActivity(matchingListIntent)
        }

        binding.makeMercenaryButton.setOnClickListener {
            val matchingListIntent = Intent(this, MakeTeamMatchingRoomActivity::class.java)
            val matchingType = MatchType.MERCENARY
            matchingListIntent.putExtra("data", matchingType as Serializable)

            this.startActivity(matchingListIntent)
        }
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
                if (tab?.text == "설정") {
                    val listView = this@MatchingListView
                    val intent = android.content.Intent(listView, SettingActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

                    listView.startActivity(intent)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //Log.d("mainTabLayout", "onTabUnselected ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //Log.d("mainTabLayout", "onTabReselected ${tab?.text}")
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
        this.binding.teamButton.isSelected = true

        this.binding.teamButton.setOnClickListener {
            this.currentMatchType = MatchType.TEAM
            it.isSelected = true

            if (this.binding.mercenaryButton.isSelected) {
                this.binding.mercenaryButton.isSelected = false
            }

            this.animateHighlighView(this.currentMatchType)
        }

        this.binding.mercenaryButton.setOnClickListener {
            this.currentMatchType = MatchType.MERCENARY
            it.isSelected = true

            if (this.binding.teamButton.isSelected) {
                this.binding.teamButton.isSelected = false
            }

            this.animateHighlighView(this.currentMatchType)
        }
    }

    fun animateHighlighView(matchType: MatchType) {
        val xPosition = if (matchType == MatchType.TEAM) 10f else 10f + this.binding.teamButton.width
        ObjectAnimator.ofFloat(this.binding.matchHighlightView, "translationX", xPosition).apply {
            duration = 300
            start()
        }
    }

    fun setupCalendarRecylcer() {
        val data: MutableList<MatchingCalendarRecycler.SmallDate> = calendarRecycler.loadData()
        var adapter = MatchingCalendarRecycler.CustomAdapter()
        adapter.listData = data
        adapter.delegate = this
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

    override fun didSelect(selectedDate: LocalDate) {
        Log.d("matchingListView", "${selectedDate}")
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






