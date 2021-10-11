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
    data class SmallDate(var weakDay: String, var date: LocalDate)

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
            var smallDate = SmallDate("${formattedTomorrow}", tomorrow)
            data.add(smallDate)
        }

        var currentDay: Int = 0

        for (no in 1..31) {
            val tomorrow = LocalDate.now().plusMonths(nextMonth).plusDays(no.toLong())
            val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("d"))
            var smallDate = SmallDate("${formattedTomorrow}", tomorrow)

            val tempDay = formattedTomorrow.toInt()

            if (tempDay > currentDay) {
                Log.d("dateTest", "${tempDay}, ${currentDay}")
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
        var storeListData = mutableSetOf<LocalDate>()

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
                storeListData.add(currentDate.date)
                this.delegate?.didSelect(storeListData)
                Toast.makeText(holder.binding.root.context, "${storeListData}", Toast.LENGTH_LONG).show()
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

            binding.dialogWeakDay.text = smallDate.weakDay
        }
    }
}