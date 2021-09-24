package com.example.taptapmatching.Setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taptapmatching.databinding.ActivitySettingBinding
import com.google.android.material.tabs.TabLayout

class SettingActivity : AppCompatActivity() {

    val binding by lazy { ActivitySettingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(this.binding.root)
        this.setupTabLayout()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        overridePendingTransition(0,0)
    }

    override fun finish() {
        super.finish()

        overridePendingTransition(0,0)
    }

    fun setupTabLayout() {
        val settingTab = binding.mainTabLayout2.newTab().setText("설정")

        binding.mainTabLayout2.addTab(binding.mainTabLayout2.newTab().setText("경기 매칭"))

        binding.mainTabLayout2.addTab(settingTab)
        binding.mainTabLayout2.selectTab(settingTab)

        binding.mainTabLayout2.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == "경기 매칭") {
                    this@SettingActivity.setResult(RESULT_OK)
                    finish()
                } else {

                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("mainTabLayout", "onTabUnselected ${tab?.text}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("mainTabLayout", "onTabReselected ${tab?.text}")
            }
        })
    }
}