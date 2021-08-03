package com.example.taptapmatching.matchingMain

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding

class MatchingFilterRecycler {

    enum class FilterType(val title: String) {
        LOCATION("장소"),
        TIME("시간대"),
        MATCH("경기"),
        PARTICIPATE("참가"),
        LEVEL("실력"),
        TEMP("테스트");
    }

    val filterList: Array<String> = arrayOf(FilterType.LOCATION.title,
                                            FilterType.TIME.title,
                                            FilterType.MATCH.title,
                                            FilterType.PARTICIPATE.title,
                                            FilterType.LEVEL.title,
                                            FilterType.TEMP.title)

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
            binding.textView5.text = "${filterInfo.title}"

            binding.textView5.setOnClickListener {
                val pos = getAdapterPosition()
                Log.d("binding.textView5.setOnClickListener", "${getFilterType(pos)}")
            }
        }

        fun getFilterType(position: Int) = when(position) {
            0 -> FilterType.LOCATION
            1 -> FilterType.TIME
            2 -> FilterType.MATCH
            3 -> FilterType.PARTICIPATE
            4 -> FilterType.LEVEL
            5 -> FilterType.TEMP
            else -> FilterType.TEMP
        }

    }

    data class FilterInfo(var title: String, var isSelected: Boolean)
}