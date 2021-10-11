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
    fun didSelect(dates: MutableSet<DialogCalendarRecycler.SmallDate>)
}

interface MainCalendarCustomAdapterDelegte {
    fun didChangeMonth(to: Int)
}

class CalendarSelectFragment() : DialogFragment(), CalendarDialogDelegate, MainCalendarCustomAdapterDelegte {

    var selectedDates: MutableSet<DialogCalendarRecycler.SmallDate> = mutableSetOf()

    private var _binding: FragmentCalendarSelectBinding? = null
    private val binding get() = _binding!!

    private val currentMonthText: String
        get() = LocalDate.now().month.value.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarSelectBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.mainCalendarMonthTextView.text = currentMonthText

        this.setupViewPager2()
        this.setupButtons()

        return view
    }

    fun setupButtons() {
        this.binding.nextButton.setOnClickListener {
            this.changeShowingMonth(1)
        }

        this.binding.backButton.setOnClickListener {
            this.changeShowingMonth(-1)
        }
    }

    fun changeShowingMonth(next: Int) {
        lastPosition = lastPosition + next

        Log.d("123123123", "${lastPosition}")
        if (lastPosition > 0) {
            val currentText: kotlin.Int = binding.mainCalendarMonthTextView.text.toString().toInt()

            //월만 바꾸는게 아니라 일도 바꿔야
            if (next == 1) {
                if (currentText == 12) {
                    binding.mainCalendarMonthTextView.text = "1"
                } else {
                    binding.mainCalendarMonthTextView.text = "${currentText + next}"
                }
            } else {
                if (currentText == 1) {
                    binding.mainCalendarMonthTextView.text = "12"
                } else {
                    binding.mainCalendarMonthTextView.text = "${currentText + next}"
                }
            }

            binding.calendarViewPager.setCurrentItem(lastPosition, true)
        }
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

    private var lastPosition: Int = 0

    fun setupViewPager2() {
        val currentMonth = LocalDate.now().dayOfMonth
        var adapter = MainCalendarCustomAdapter(requireActivity(), currentMonth)
        adapter.delegate = this
        binding.calendarViewPager.adapter = adapter

        binding.calendarViewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                this.changeShowingMonth(position)
            }

            fun changeShowingMonth(position: Int) {
                val currentText: Int = binding.mainCalendarMonthTextView.text.toString().toInt()

                if (lastPosition > position) {
                    binding.mainCalendarMonthTextView.text = this.makeNewMonth(currentText, -1).toString()
                } else if (lastPosition == position) {
                    binding.mainCalendarMonthTextView.text = currentText.toString()
                } else {
                    binding.mainCalendarMonthTextView.text = this.makeNewMonth(currentText, 1).toString()
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

    override fun didSelect(dates: MutableSet<DialogCalendarRecycler.SmallDate>) {
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

