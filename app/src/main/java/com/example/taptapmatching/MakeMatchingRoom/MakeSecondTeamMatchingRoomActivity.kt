package com.example.taptapmatching.MakeMatchingRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.taptapmatching.databinding.ActivityMakeSecondTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.MakematchingroomlayoutBinding

class MakeSecondTeamMatchingRoomActivity : AppCompatActivity(), TeamIntructionFragmentDelegate {

    val binding by lazy { ActivityMakeSecondTeamMatchingRoomBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)

        this.setupTeamIntruductionButton()
        this.setupAddTeamInfoButton()
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
        Toast.makeText(this, "${data.map { it.text }}", Toast.LENGTH_SHORT).show()
    }
}