package com.example.taptapmatching.matchingMain.SelectCalendar

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.DialogCalendarLayoutBinding
import com.example.taptapmatching.matchingMain.CalendarDialogDelegate
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

public class DialogCalendarRecycler {
    data class SmallDate(var weakDay: String, var date: LocalDate, var isEmptyDate: Boolean)

    fun loadData(position: Int): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언
        return this.getNextMonthDate(position.toLong())
    }

    fun getNextMonthDate(nextMonth: Long): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언

        val today = LocalDate.now().plusMonths(nextMonth)
        val formattedToday = today.format(DateTimeFormatter.ofPattern("d")).toInt()-1

        for (no in -formattedToday..0) {
            val tomorrow = LocalDate.now().plusMonths(nextMonth).plusDays(no.toLong())
            val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("d"))
            var smallDate = SmallDate("${formattedTomorrow}", tomorrow, false)
            data.add(smallDate)
        }

        val first = data.first()
        val emptyDate = SmallDate("${formattedToday}", LocalDate.now(), true)

        if (first.date.dayOfWeek == DayOfWeek.SUNDAY) {

        } else if (first.date.dayOfWeek == DayOfWeek.MONDAY) {
            data.add(0, emptyDate)
        } else if (first.date.dayOfWeek == DayOfWeek.TUESDAY) {
            data.add(0, emptyDate)
            data.add(0, emptyDate)
        } else if (first.date.dayOfWeek == DayOfWeek.WEDNESDAY) {
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
        } else if (first.date.dayOfWeek == DayOfWeek.THURSDAY) {
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
        } else if (first.date.dayOfWeek == DayOfWeek.FRIDAY) {
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
        } else if (first.date.dayOfWeek == DayOfWeek.SATURDAY) {
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
            data.add(0, emptyDate)
        }

        var currentDay: Int = 0

        for (no in 1..31) {
            val tomorrow = LocalDate.now().plusMonths(nextMonth).plusDays(no.toLong())
            val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("d"))
            var smallDate = SmallDate("${formattedTomorrow}", tomorrow, false)

            val tempDay = formattedTomorrow.toInt()

            if (tempDay > currentDay) {
                data.add(smallDate)
            } else {
                break
            }

            currentDay = tempDay
        }

        return data
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<SmallDate>()
        var storeListData = mutableSetOf<SmallDate>()

        public var delegate: CalendarDialogDelegate? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = DialogCalendarLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)

            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val currentDate = listData.get(position)
            holder.setMemo(currentDate)

            holder.binding.root.setOnClickListener {
                storeListData.add(currentDate)
                this.delegate?.didSelect(storeListData)

                if (it.isSelected == false) {
                    holder.binding.dialogWeakDay.setBackgroundColor(Color.BLACK)
                } else {
                    holder.binding.dialogWeakDay.setBackgroundColor(Color.WHITE)
                }

                it.setSelected(!it.isSelected)
                holder.binding.dialogWeakDay.isSelected

                Toast.makeText(holder.binding.root.context, "${it.isSelected}", Toast.LENGTH_LONG).show()
            }
        }
    }

    class Holder(val binding: DialogCalendarLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMemo(smallDate: SmallDate) {
            if (smallDate.date.dayOfWeek == DayOfWeek.SATURDAY) {
                binding.dialogWeakDay.setTextColor(Color.BLUE)
            } else if (smallDate.date.dayOfWeek == DayOfWeek.SUNDAY) {
                binding.dialogWeakDay.setTextColor(Color.RED)
            } else {
                binding.dialogWeakDay.setTextColor(Color.BLACK)
            }

            if (smallDate.isEmptyDate == true) {
                binding.dialogWeakDay.text = ""
            } else {
                binding.dialogWeakDay.text = smallDate.weakDay
            }
        }
    }
}