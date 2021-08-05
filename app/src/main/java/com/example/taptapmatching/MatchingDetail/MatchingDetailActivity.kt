package com.example.taptapmatching.MatchingDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taptapmatching.databinding.ActivityMatchingDetailBinding
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding

class MatchingDetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityMatchingDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)
    }

}