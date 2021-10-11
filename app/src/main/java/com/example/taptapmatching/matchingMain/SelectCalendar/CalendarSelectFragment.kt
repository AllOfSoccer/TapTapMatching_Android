package com.example.taptapmatching.matchingMain

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.taptapmatching.MatchingListView
import com.example.taptapmatching.databinding.DialogCalendarLayoutBinding
import com.example.taptapmatching.databinding.FragmentCalendarSelectBinding
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding
import com.example.taptapmatching.matchingMain.SelectCalendar.DialogCalendarRecycler
import com.example.taptapmatching.matchingMain.SelectCalendar.recycler_calendar_fragment
import java.time.LocalDate
import java.time.Month

interface CalendarDialogDelegate {
    fun didSelect(dates: MutableSet<LocalDate>)
}

interface MainCalendarCustomAdapterDelegte {
    fun didChangeMonth(to: Int)
}

class CalendarSelectFragment() : DialogFragment(), CalendarDialogDelegate, MainCalendarCustomAdapterDelegte {

    var selectedDates: MutableSet<LocalDate> = mutableSetOf()

    private var _binding: FragmentCalendarSelectBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 시작시 필요 데이터: 현재 월, 현재 선택된 일자
        _binding = FragmentCalendarSelectBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.mainCalendarMonthTextView.text = LocalDate.now().month.value.toString()
        Log.d("wndgus", "${LocalDate.now().dayOfMonth.toString()}, ${LocalDate.now()}")

        this.setupViewPager2()

        return view
    }

    fun setupViewPager2() {
        val currentMonth = LocalDate.now().dayOfMonth
        var adapter = MainCalendarCustomAdapter(requireActivity(), currentMonth)
        adapter.delegate = this
        binding.calendarViewPager.adapter = adapter

        binding.calendarViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            var lastPosition: Int = 0

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                Log.d("setupViewPager", "Called ${position}")

                val currentText: Int = binding.mainCalendarMonthTextView.text.toString().toInt()

                if (lastPosition > position) {
                    val a = makeNewMonth(currentText, -1)
                    binding.mainCalendarMonthTextView.text = a.toString()
                } else if (lastPosition == position) {
                    binding.mainCalendarMonthTextView.text = currentText.toString()
                } else {
                    val b = this.makeNewMonth(currentText, 1)
                    binding.mainCalendarMonthTextView.text = b.toString()
                }

                lastPosition = position
            }

            fun makeNewMonth(current: Int, position: Int): Int {
                val new = current + position

                if (new > 12) {
                    return 1
                } else if (new == 0) {
                    return 12
                } else {
                    return new
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        val currentActivity = this.activity

        if (currentActivity is MatchingListView) {
            currentActivity.selectedDates = this.selectedDates
        }
    }

    override fun didSelect(dates: MutableSet<LocalDate>) {
        this.selectedDates = dates
    }

    override fun didChangeMonth(to: Int) {
        Log.d("didChangeMonth", "to${to}")
    }
}

    class MainCalendarCustomAdapter(fragmentActivity: FragmentActivity, currentMonth: Int): FragmentStateAdapter(fragmentActivity) {

        var delegate: MainCalendarCustomAdapterDelegte? = null

        override fun getItemCount(): Int {
            return 24
        }

        override fun createFragment(position: Int): Fragment {
            return recycler_calendar_fragment(position)
        }

        override fun getItemId(position: Int): Long {
            delegate?.didChangeMonth(position)
            return super.getItemId(position)
        }

    }

