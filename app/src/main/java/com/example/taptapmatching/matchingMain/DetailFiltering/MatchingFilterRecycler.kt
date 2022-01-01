package com.example.taptapmatching.matchingMain

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.FragmentRestorefilteringBinding
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding

interface  MatchingFilterRecyclerDelegate {
    fun didSelectFilterType(type: MatchingFilterRecycler.FilterType)
}

class MatchingFilterRecycler {

    public enum class FilterType(val title: String) {
        RESTORE("필터 되돌리"),
        LOCATION("장소"),
        TIME("시간대"),
        MATCH("경기"),
        PARTICIPATE("참가"),
        LEVEL("실력"),
        TEMP("테스트");
    }

    val filterList: Array<String> = arrayOf(FilterType.RESTORE.title,
                                            FilterType.LOCATION.title,
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

    class CustomAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val restoreFilter: Int = 0
        val default: Int = 1

        var listData = mutableListOf<FilterInfo>()
        var delegate: MatchingFilterRecyclerDelegate? = null

        override fun getItemViewType(position: Int): Int {
            if (getFilterType(position) == FilterType.RESTORE) {
                return restoreFilter
            } else {
                return  default
            }
        }

        // 한 화면에 그려지는 아이템 개수만큼 레이아웃 생
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            Log.d("onCreateViewHolder222", "${viewType}")

            if (viewType == restoreFilter) {
                val binding = FragmentRestorefilteringBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false)

                delegate = parent.context as MatchingFilterRecyclerDelegate

                return FilterRestoreHolder(binding)
            } else {
                val binding = FragmentSmallFilteringBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false)

                delegate = parent.context as MatchingFilterRecyclerDelegate

                return Holder(binding)
            }
        }

        // 목록의 총 데이터의 개수
        override fun getItemCount(): Int {
            return listData.size
        }

        // 생성된 뷰 홀더를 화면에 보여줌
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val memo = listData.get(position)

            if (holder is Holder) {
                holder.setMemo(memo)

                holder.binding.root.setOnClickListener {
                    holder.binding.root.run {
                        isSelected = !isSelected
                    }

                    val pos = holder.getAdapterPosition()
                    this.delegate?.didSelectFilterType(getFilterType(pos))
                }
            }

            if (holder is FilterRestoreHolder) {
                holder.binding.root.setOnClickListener {
                    holder.binding.root.run {
                        isSelected = !isSelected
                    }

                    val pos = holder.getAdapterPosition()
                    this.delegate?.didSelectFilterType(getFilterType(pos))
                }
            }

        }

        fun getFilterType(position: Int) = when(position) {
            0 -> FilterType.RESTORE
            1 -> FilterType.LOCATION
            2 -> FilterType.TIME
            3 -> FilterType.MATCH
            4 -> FilterType.PARTICIPATE
            5 -> FilterType.LEVEL
            6 -> FilterType.TEMP
            else -> FilterType.TEMP
        }
    }

    class Holder(val binding: FragmentSmallFilteringBinding): RecyclerView.ViewHolder(binding.root) {
        // binding을 생성 시점에 전달받아 해당 바인딩에 있는 데이터를 세팅
        fun setMemo(filterInfo: FilterInfo) {
            binding.textView5.text = "${filterInfo.title}"
        }

    }

    class FilterRestoreHolder(val binding: FragmentRestorefilteringBinding): RecyclerView.ViewHolder(binding.root) {

    }

    data class FilterInfo(var title: String, var isSelected: Boolean)
}