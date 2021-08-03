package com.example.taptapmatching.matchingMain

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

        for (no in 0..48) {
            val numberOfRow: Int = (no % 7)
            val number = (no / 7) + (numberOfRow * 7)
            val tomorrow = LocalDate.now().plusDays(number.toLong())
            val formattedTomorrow = tomorrow.format(DateTimeFormatter.ofPattern("d"))
            var smallDate = SmallDate("${formattedTomorrow}", tomorrow)
            data.add(smallDate)
        }

        return data
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<SmallDate>()

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
            val memo = listData.get(position)
            holder.setMemo(memo)
        }
    }

    class Holder(val binding: DialogCalendarLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMemo(smallDate: SmallDate) {
            binding.dialogWeakDay.text = smallDate.weakDay
        }

        init {
            binding.root.setOnClickListener {
                Toast.makeText(binding.root.context, "클릭된 아이템 = ${binding.dialogWeakDay.text}", Toast.LENGTH_LONG).show()
            }
        }
    }
}