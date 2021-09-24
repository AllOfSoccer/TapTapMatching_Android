package com.example.taptapmatching.Setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taptapmatching.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    val binding by lazy { ActivitySettingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(this.binding.root)
    }
}