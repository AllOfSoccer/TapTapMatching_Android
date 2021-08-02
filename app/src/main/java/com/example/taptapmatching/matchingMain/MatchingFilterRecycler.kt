package com.example.taptapmatching.matchingMain

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding

class MatchingFilterRecycler {

    val filterList: Array<String> = arrayOf("장소", "시간대", "경기", "참가", "실력", "테스트")

    fun loadData(): MutableList<FilterInfo> {
        val result: MutableList<FilterInfo> = mutableListOf() // 컬렉션을 선언

        for (title in filterList) {
            var memo = FilterInfo(title, false)
            result.add(memo)
        }

        return result
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<FilterInfo>()

        // 한 화면에 그려지는 아이템 개수만큼 레이아웃 생
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = FragmentSmallFilteringBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
            return Holder(binding)
        }

        // 목록의 총 데이터의 개수
        override fun getItemCount(): Int {
            return listData.size
        }

        // 생성된 뷰 홀더를 화면에 보여줌
        override fun onBindViewHolder(holder: Holder, position: Int) {
            val memo = listData.get(position)
            holder.setMemo(memo)
        }
    }

    class Holder(val binding: FragmentSmallFilteringBinding): RecyclerView.ViewHolder(binding.root) {
        // binding을 생성 시점에 전달받아 해당 바인딩에 있는 데이터를 세팅
        fun setMemo(filterInfo: FilterInfo) {

            binding.textView5.setOnClickListener {
                val pos = getAdapterPosition()
                Log.d("ddd", "필터종류 선택됨. ${pos}")
            }

            binding.textView5.text = "${filterInfo.title}"
            Log.d("dd", "${filterInfo.title}")
        }

    }

    data class FilterInfo(var title: String, var isSelected: Boolean)
}