package com.example.taptapmatching.MakeMatchingRoom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.FragmentTeamIntruductionBinding
import com.example.taptapmatching.databinding.MatchingListLayoutBinding
import com.example.taptapmatching.databinding.TeamIntroductionLayoutBinding
import com.example.taptapmatching.matchingMain.MatchingData
import com.example.taptapmatching.matchingMain.MatchingDataSource
import com.example.taptapmatching.matchingMain.MatchingListRecycler
import com.example.taptapmatching.matchingMain.MatchingListRecyclerDelegate

interface TeamIntructionFragmentDelegate {
    fun onDataPass(data: List<TeamIntroductionInfo>)
}

class TeamIntructionFragment: DialogFragment(), TeamIntroductionRecyclerDelegate {
    private var _binding: FragmentTeamIntruductionBinding? = null
    private val binding get() = _binding!!

    var delegate: TeamIntructionFragmentDelegate? = null

    private var recycler = TeamIntroductionRecycler()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeamIntruductionBinding.inflate(inflater, container, false)
        val view = binding.root

        this.setupTeamIntroductionSampleRecyclerView()
        this.setupTeamIntroductionSelectButton()

        return view
    }

    fun setupTeamIntroductionSampleRecyclerView() {
        this.binding.teamIntructionSampleRecyclerView.adapter = recycler.makeAdapter(this)
        this.binding.teamIntructionSampleRecyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }

    fun setupTeamIntroductionSelectButton() {
        this.binding.teamIntroductionSelectButton.setOnClickListener {
            this.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val data = this.recycler.listData.filter{ it.isSelected }
        this.delegate?.onDataPass(this.recycler.listData.filter{ it.isSelected })
    }

    override fun didSelect(matching: TeamIntroductionInfo) {
        Toast.makeText(this.context, "${matching.isSelected}", Toast.LENGTH_SHORT).show()
    }
}

// MARK: - Data
public data class TeamIntroductionInfo(val text: String, var isSelected: Boolean)

// MARK: - Delegate
interface TeamIntroductionRecyclerDelegate {
    fun didSelect(matching: TeamIntroductionInfo)
}

// MARK: - Recycler
public class TeamIntroductionRecycler {

    fun makeAdapter(delegate: TeamIntroductionRecyclerDelegate?): RecyclerView.Adapter<Holder> {
        var adapter = CustomAdapter(this.listData)
        adapter.delegate = delegate
        return adapter
    }

    var listData = mutableListOf(
        TeamIntroductionInfo("편하게 연락주세요.", false),
        TeamIntroductionInfo("매너있게 운동하실 분 기다립니다.", false),
        TeamIntroductionInfo("문자로 연락주세요.", false),
        TeamIntroductionInfo("연락 시 팀 소개 부탁드립니다.",false),
        TeamIntroductionInfo("직접입력해주세요.", false)
    )

    class CustomAdapter(listData: MutableList<TeamIntroductionInfo>): RecyclerView.Adapter<Holder>() {

        private var listData = listData

        var delegate: TeamIntroductionRecyclerDelegate? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = TeamIntroductionLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val teamIntroductionInfo = listData.get(position)
            holder.setMatching(teamIntroductionInfo)

            holder.binding.matchingListContentLayout.setOnClickListener {
                it.setSelected(!it.isSelected)
                teamIntroductionInfo.isSelected = it.isSelected
                this.delegate?.didSelect(teamIntroductionInfo)
            }
        }
    }

    class Holder(val binding: TeamIntroductionLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun setMatching(info: TeamIntroductionInfo) {
            binding.teamIntroductionTextView.text = info.text
            binding.teamIntroductionButton.isSelected = info.isSelected
        }
    }
}