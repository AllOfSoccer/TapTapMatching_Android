package com.example.taptapmatching.MakeMatchingRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taptapmatching.databinding.ActivityMakeTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.MakematchingroomlayoutBinding
import com.example.taptapmatching.matchingMain.CalendarSelectFragment

class MakeTeamMatchingRoomActivity : AppCompatActivity() {

    val binding by lazy { MakematchingroomlayoutBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)

        this.setupDataAndTimeButtonListener()
    }

    fun setupDataAndTimeButtonListener() {
        binding.dateAndTimeSelectButton.setOnClickListener {
            val calendarSelectFragment = CalendarSelectFragment()
            calendarSelectFragment.show(supportFragmentManager, "CalendarSelectFragment")
        }
    }

    fun setupPlaceSelectButtonListener() {
        binding.placeSelectButton.setOnClickListener {
            // 장소 선택 팝업을 띄운다.
        }
    }
}