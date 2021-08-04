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

    fun loadData(): MutableList<String> {
        var result: MutableList<String> = mutableListOf() // 컬렉션을 선언

        result.add("1")
        result.add("2")
        result.add("3")
        result.add("4")
        result.add("5")

        return result
    }

    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<String>()

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
                Log.d("onBindViewHolder", "${memo}")
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