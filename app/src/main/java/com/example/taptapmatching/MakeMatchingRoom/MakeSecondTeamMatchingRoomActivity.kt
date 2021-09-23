package com.example.taptapmatching.MakeMatchingRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taptapmatching.databinding.ActivityMakeSecondTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.MakematchingroomlayoutBinding

class MakeSecondTeamMatchingRoomActivity : AppCompatActivity() {

    val binding by lazy { ActivityMakeSecondTeamMatchingRoomBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)
    }
}