package com.example.taptapmatching

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.example.taptapmatching.databinding.SmallcalendarRecyclerBinding
import com.example.taptapmatching.matchingFilter.smallFilteringFragment
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class SmallDate(var weakDate: String, var weakDay: String)
data class SmallFilter(var title: String, var isSelected: Boolean)

class MatchingListView : AppCompatActivity() {

    val binding by lazy { ActivityMatchingListViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setFragment()

        val data: MutableList<SmallDate> = loadData()
        var adapter = MatchingListView.CustomAdapter()
        adapter.listData = data
        binding.smallCalendarRecyclerView.adapter = adapter
        binding.smallCalendarRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    fun setFragment() {
        val listFragment: smallFilteringFragment = smallFilteringFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.filterFrameLayout, listFragment)
        transaction.commit()
    }

    fun loadData(): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언

        for (no in 0..1000) {
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

