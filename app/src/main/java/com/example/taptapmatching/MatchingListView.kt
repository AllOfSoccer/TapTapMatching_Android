package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding

class MatchingListView : AppCompatActivity() {

    val binding by lazy { ActivityMatchingListViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}