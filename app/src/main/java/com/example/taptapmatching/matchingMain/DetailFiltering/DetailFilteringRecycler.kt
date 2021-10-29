package com.example.taptapmatching.matchingMain

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.DetailfilteringRecyclerBinding
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding


interface  DetailFilteringRecyclerDelegate {
    fun didSelectFilterType(type: MatchingFilterRecycler.FilterType)
}

class DetailFilteringRecycler {

    var type: MatchingFilterRecycler.FilterType? = null

    fun loadData(type: MatchingFilterRecycler.FilterType): MutableList<String> {
        var result: MutableList<String> = mutableListOf() // 컬렉션을 선언

        when {
            MatchingFilterRecycler.FilterType.LOCATION == type -> result = mutableListOf("서울", "경기-북부", "경기-남부", "인천,부천", "기타지")
            MatchingFilterRecycler.FilterType.TIME == type -> result = mutableListOf("1", "2", "3")
            MatchingFilterRecycler.FilterType.MATCH == type -> result = mutableListOf("서울", "경기-북부", "경기-남부", "인천,부천", "기타지")
            MatchingFilterRecycler.FilterType.PARTICIPATE == type -> result = mutableListOf("서울", "경기-북부", "경기-남부", "인천,부천", "기타지")
            MatchingFilterRecycler.FilterType.LEVEL == type -> result = mutableListOf("서울", "경기-북부", "경기-남부", "인천,부천", "기타지")
            MatchingFilterRecycler.FilterType.TEMP == type -> result = mutableListOf("서울", "경기-북부", "경기-남부", "인천,부천", "기타지")
            else -> result = mutableListOf()
        }

        return result
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<String>()
        var selectedListData = mutableSetOf<String>()

        // 한 화면에 그려지는 아이템 개수만큼 레이아웃 생
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = DetailfilteringRecyclerBinding.inflate(
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
            holder.setButton(memo)

            holder.binding.button5.setOnClickListener {
                this.selectedListData.add(memo)
                Log.d("onBindViewHolder", "${this.selectedListData}")
            }

        }
    }

    class Holder(val binding: DetailfilteringRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        // binding을 생성 시점에 전달받아 해당 바인딩에 있는 데이터를 세팅
        fun setButton(title: String) {
            binding.button5.text = title
        }

    }

}