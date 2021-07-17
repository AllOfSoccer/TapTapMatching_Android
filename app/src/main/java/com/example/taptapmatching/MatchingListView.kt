package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.example.taptapmatching.databinding.ItemRecyclerBinding
import com.example.taptapmatching.databinding.SmallcalendarRecyclerBinding
import java.text.SimpleDateFormat

data class SmallDate(var weakDate: String, var weakDay: String)

class MatchingListView : AppCompatActivity() {

    val binding by lazy { ActivityMatchingListViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data: MutableList<SmallDate> = loadData()
        var adapter = MatchingListView.CustomAdapter()
        adapter.listData = data
        binding.smallCalendarRecyclerView.adapter = adapter
        binding.smallCalendarRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun loadData(): MutableList<SmallDate> {
        val data: MutableList<SmallDate> = mutableListOf() // 컬렉션을 선언

        for (no in 1..100) {
            val date = System.currentTimeMillis()
            var smallDate = SmallDate("월", "7")
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
    }
}

