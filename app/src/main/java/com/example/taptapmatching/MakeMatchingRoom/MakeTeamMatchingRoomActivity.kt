package com.example.taptapmatching.MakeMatchingRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taptapmatching.databinding.ActivityMakeTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.ActivityMatchingDetailBinding
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;

class MakeTeamMatchingRoomActivity : AppCompatActivity() {

    val binding by lazy { ActivityMakeTeamMatchingRoomBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)
    }
}