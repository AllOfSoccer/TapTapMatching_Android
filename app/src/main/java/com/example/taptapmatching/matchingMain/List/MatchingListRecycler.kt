package com.example.taptapmatching.matchingMain

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.MatchingListLayoutBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface MatchingListRecyclerDelegate {
    fun didSelect(matching: MatchingData)
}

public class MatchingListRecycler {
    fun loadData(): MutableList<MatchingData> {
        return MatchingDataSource.shared.list
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<MatchingData>()
        var delegate: MatchingListRecyclerDelegate? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = MatchingListLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val matching = listData.get(position)
            holder.setMatching(matching)

            holder.binding.matchingListContentLayout.setOnClickListener {
                this.delegate?.didSelect(matching)
            }
        }
    }

    //RecyclerView.Holder가 하는 역할? --> 데이터를 매칭 시킨다. 데이터 바인딩을 함.
    class Holder(val binding: MatchingListLayoutBinding): RecyclerView.ViewHolder(binding.root) {

        fun setMatching(matching: MatchingData) {
            binding.bottomTime.text = "${matching.time}"
            binding.topDate.text = "${matching.time}"
            binding.locationNameTextView.text = matching.location
            binding.detailTextView.text = "조합해서 만들어야합니다!!!!"
            binding.recruitStateTextView.text = "모집중"
        }
    }
}