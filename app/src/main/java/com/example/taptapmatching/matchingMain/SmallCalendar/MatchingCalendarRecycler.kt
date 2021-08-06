package com.example.taptapmatching.matchingMain.SmallCalendar

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.SmallcalendarRecyclerBinding
import kotlinx.coroutines.selects.select
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

public class MatchingCalendarRecycler {
    data class SmallDate(var weakDate: String, var weakDay: String, var date: LocalDate)

    fun loadData(): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언

        for (no in 0..99) {
            val tomorrow = LocalDate.now().plusDays(no.toLong())
            val abc = tomorrow.dayOfWeek.toKorean()
            val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("dd"))
            var smallDate = SmallDate("${abc}", "${formattedTomorrow}", tomorrow)
            data.add(smallDate)
        }

        return data
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<SmallDate>()

        private var selectedDate: MutableSet<LocalDate> = mutableSetOf<LocalDate>()

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

            var selectedColor = Color.WHITE
            var isSeleted: Boolean = false

            if (selectedDate.isEmpty() == false) {
                if (memo.date == selectedDate.first()) {
                    isSeleted = true
                    selectedColor = Color.parseColor("#f2f3f5")
                }
            }

            var shape = GradientDrawable()
            val round = (holder.binding.root.width / 2).toFloat()

            shape.setCornerRadius(round)
            shape.setColor(selectedColor)

            holder.binding.root.setBackground(shape)

            holder.binding.root.setOnClickListener {
                selectedDate.add(listData[position].date)
                selectedDate = selectedDate.filter { it == listData[position].date }.toSet().toMutableSet()

                Toast.makeText(holder.binding.root.context, "클릭된 아이템 = ${selectedDate}", Toast.LENGTH_LONG).show()

                notifyDataSetChanged()
            }
        }
    }

    class Holder(val binding: SmallcalendarRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMemo(smallDate: SmallDate) {
            binding.weakDate.text = smallDate.weakDate
            binding.weakDay.text = smallDate.weakDay

            if (smallDate.weakDate == "토") {
                binding.weakDate.setTextColor(Color.parseColor("#2c81d0"))
                binding.weakDay.setTextColor(Color.parseColor("#2c81d0"))
            } else if (smallDate.weakDate == "일") {
                binding.weakDate.setTextColor(Color.parseColor("#d63030"))
                binding.weakDay.setTextColor(Color.parseColor("#d63030"))
            } else {
                binding.weakDate.setTextColor(Color.BLACK)
                binding.weakDay.setTextColor(Color.BLACK)
            }
        }
    }
}
