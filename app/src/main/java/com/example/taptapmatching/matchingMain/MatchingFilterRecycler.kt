package com.example.taptapmatching.matchingMain

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding

class MatchingFilterRecycler {
    fun loadData(): MutableList<FilterInfo> {
        val result: MutableList<FilterInfo> = mutableListOf() // 컬렉션을 선언

        for (no in 1..100) {
            val title = "장소 ${no}"
            var memo = FilterInfo(title, false)
            result.add(memo)
        }

        return result
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<FilterInfo>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = FragmentSmallFilteringBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        //생성된 뷰 홀더를 화면에 보여줌
        override fun onBindViewHolder(holder: Holder, position: Int) {
            val memo = listData.get(position)
            holder.setMemo(memo)
        }
    }

    class Holder(val binding: FragmentSmallFilteringBinding): RecyclerView.ViewHolder(binding.root) {
        // binding을 생성 시점에 전달받아 해당 바인딩에 있는 데이터를 세팅
        fun setMemo(filterInfo: FilterInfo) {
            binding.textView5.text = "${filterInfo.title}"
            Log.d("dd", "${filterInfo.title}")
        }
    }

    data class FilterInfo(var title: String, var isSelected: Boolean)
}