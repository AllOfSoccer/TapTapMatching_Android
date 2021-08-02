package com.example.taptapmatching.matchingMain

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.SmallcalendarRecyclerBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

public class MatchingCalendarRecycler {
    data class SmallDate(var weakDate: String, var weakDay: String)

    fun loadData(): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언

        for (no in 0..99) {
            val tomorrow = LocalDate.now().plusDays(no.toLong())
            val abc = tomorrow.dayOfWeek.toKorean()
            val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("dd"))
            var smallDate = SmallDate("${abc}", "${formattedTomorrow}")
            data.add(smallDate)
        }

        return data
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<SmallDate>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = SmallcalendarRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val memo = listData.get(position)
            holder.setMemo(memo)
        }
    }

    class Holder(val binding: SmallcalendarRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMemo(smallDate: SmallDate) {
            binding.weakDate.text = smallDate.weakDate
            binding.weakDay.text = smallDate.weakDay
        }

        init {
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "클릭된 아이템 = ${binding.weakDate.text}", Toast.LENGTH_LONG).show()
            }
        }
    }
}

fun DayOfWeek.toKorean() : String {
    val temp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.value
    } else {
        throw Exception("예외 ㅠㅠ")
    }

    return when(temp){
        DayOfWeek.SATURDAY.value -> "토"
        DayOfWeek.SUNDAY.value -> "일"
        DayOfWeek.MONDAY.value -> "월"
        DayOfWeek.TUESDAY.value -> "화"
        DayOfWeek.WEDNESDAY.value -> "수"
        DayOfWeek.THURSDAY.value -> "목"
        DayOfWeek.FRIDAY.value -> "금"
        else -> "x"
    }
}