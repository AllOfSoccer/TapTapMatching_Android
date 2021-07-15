package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.taptapmatching.databinding.ActivityButton1Binding

class Button1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityButton1Binding(LayoutInflater)
        setContentView(binding.root)

        Log.d("UIText", "Button1Activity 진입.")
    }
}