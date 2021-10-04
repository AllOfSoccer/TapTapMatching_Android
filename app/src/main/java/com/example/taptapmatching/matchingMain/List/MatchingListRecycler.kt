package com.example.taptapmatching.matchingMain

import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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
    fun loadData(myCallback: (callBackValue: MutableList<MatchingData>) -> Unit) {
        MatchingDataSource.shared.getData() { result ->
            myCallback.invoke(result)
        }
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
//            binding.bottomTime.text = "${matching.time}"
//            binding.topDate.text = "${matching.time}"
            binding.locationNameTextView.text = matching.location
            binding.detailTextView.text = "${matching.teamName}" //TODO: 임의로 teamName넣었음. detail로 바꿔야함
            //binding.recruitStateTextView.text = "모집중"
        }
    }
}

public class DividerItemDecoration: RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.bottom = 16
    }
}