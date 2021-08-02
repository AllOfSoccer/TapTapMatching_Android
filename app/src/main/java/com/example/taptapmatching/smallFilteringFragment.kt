package com.example.taptapmatching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.FragmentSmallFilteringBinding

class smallFilteringFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = loadData()
        var adapter = CustomAdapter()
        adapter.listData = data

        var binding = FragmentSmallFilteringBinding.inflate(inflater, container, false)
        //binding.filterRecyclerView.adapter = adapter
        //binding.filterRecyclerView.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true)
        // 아래 라인을 binding을 사용하는 코드로 변경
        return binding.root
    }

    fun loadData(): MutableList<FilterInfo> {
        val data: MutableList<FilterInfo> = mutableListOf() // 컬렉션을 선언

        for (no in 1..100) {
            val title = "장소 ${no}"
            val date = System.currentTimeMillis()
            var memo = FilterInfo(no, title)
            data.add(memo)
        }

        return data
    }

    class CustomAdapter: RecyclerView.Adapter<smallFilteringFragment.Holder>() {

        var listData = mutableListOf<FilterInfo>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): smallFilteringFragment.Holder {
            val binding = FragmentSmallFilteringBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
            return smallFilteringFragment.Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        //생성된 뷰 홀더를 화면에 보여줌
        override fun onBindViewHolder(holder: smallFilteringFragment.Holder, position: Int) {
            val memo = listData.get(position)
            holder.setMemo(memo)
        }
    }

    //Holder가 하는 역할은?
    // 개별 데이터에 대응.
    // 화면에 보이는 개수의 홀더만 생성. (위쪽의 홀더를 계속해서 재사용한다)
    class Holder(val binding: FragmentSmallFilteringBinding): RecyclerView.ViewHolder(binding.root) {
        // binding을 생성 시점에 전달받아 해당 바인딩에 있는 데이터를 세팅
        fun setMemo(memo: FilterInfo) {
            binding.textView5.text = "${memo.title}"
        }
    }

    data class FilterInfo(var no: Int, var title: String)

    // inflater : 레이아웃 파일을 로드가히 위한 레이아웃 인플레이터를 기본으로 제공.
    // Container: 프래그먼트 레이아웃이 배치되는 부모 레이아웃 (액티비티의 레이아웃)
    // savedInstanceState: 상태 값 저장을 위한 보조 도구. 액티비티의 onCreate의 파라미터와 동일하게 동작.
}