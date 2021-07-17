package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.taptapmatching.databinding.ActivityButton1Binding
import com.example.taptapmatching.databinding.ActivityButton2Binding

class ButtonActivity2 : AppCompatActivity() {

    val binding by lazy { ActivityButton2Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var data = listOf("--선택하세요--", "1월", "2월", "3월", "4월", "5월", "6월")
        var adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data)
        binding.spinner.adapter = adapter
    }
}