package com.example.taptapmatching.MakeMatchingRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taptapmatching.databinding.ActivityMakeSecondTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.MakematchingroomlayoutBinding

class MakeSecondTeamMatchingRoomActivity : AppCompatActivity(), TeamIntructionFragmentDelegate {

    val binding by lazy { ActivityMakeSecondTeamMatchingRoomBinding.inflate(layoutInflater) }

    private var recycler = TeamIntroductionRecycler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)

        this.setupTeamIntruductionButton()
        this.setupAddTeamInfoButton()
        this.setupTeamIntroductionSampleRecyclerView()
    }

    fun setupTeamIntruductionButton() {
        this.binding.teamIntrudctionButton.setOnClickListener {
            val teamIntructionFragment = TeamIntructionFragment()
            teamIntructionFragment.show(supportFragmentManager, "TeamIntructionFragment")
        }
    }

    fun setupAddTeamInfoButton() {
        this.binding.addTeamInfoButton.setOnClickListener {
            val teamIntructionFragment = TeamIntructionFragment()
            teamIntructionFragment.delegate = this
            teamIntructionFragment.show(supportFragmentManager, "TeamIntructionFragment")
        }
    }

    override fun onDataPass(data: List<TeamIntroductionInfo>) {
        Log.d("isCalled?", "${data.map { it.text }}")
        this.recycler.listData = data.toMutableList()
        this.binding.recyclerView3.setHeight(data.count() * 100)

        var adpater = this.binding.recyclerView3.adapter as? TeamIntroductionRecycler.CustomAdapter
        adpater?.update(data.toMutableList())
        adpater?.notifyDataSetChanged()

        Toast.makeText(this, "${this.binding.recyclerView3.adapter}", Toast.LENGTH_SHORT).show()
    }

    fun setupTeamIntroductionSampleRecyclerView() {
        this.recycler.listData.clear()
        this.binding.recyclerView3.adapter = recycler.makeAdapter(null)
        this.binding.recyclerView3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}

fun View.setHeight(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.height = value
        layoutParams = lp
    }
}

fun View.setWidth(value: Int) {
    val lp = layoutParams
    lp?.let {
        lp.width = value
        layoutParams = lp
    }
}
