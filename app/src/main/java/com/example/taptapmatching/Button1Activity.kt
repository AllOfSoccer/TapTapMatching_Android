package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.taptapmatching.databinding.ActivityButton1Binding

class Button1Activity : AppCompatActivity() {

    val binding by lazy { ActivityButton1Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = "${intent.getIntExtra("from2", 0)}"

        Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show()
    }
}