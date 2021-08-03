package com.example.taptapmatching.matchingMain

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.DialogCalendarLayoutBinding
import com.example.taptapmatching.databinding.SmallcalendarRecyclerBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

public class DialogCalendarRecycler {
    data class SmallDate(var weakDay: String, var date: LocalDate)

    fun loadData(): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언
        return this.getNextMonthDate(0)
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

        for (no in 0..31) {
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

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = DialogCalendarLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)

            val width = parent.measuredWidth
            binding.dialogRecyclerView
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
                Toast.makeText(holder.binding.root.context, "${storeListData}", Toast.LENGTH_LONG).show()
            }
        }
    }

    class Holder(val binding: DialogCalendarLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMemo(smallDate: SmallDate) {
            binding.dialogWeakDay.text = smallDate.weakDay
        }
    }
}