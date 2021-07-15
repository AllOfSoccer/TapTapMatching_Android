package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.example.taptapmatching.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.button1.setOnClickListener {
            Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show()
        }

        binding.button2.setOnClickListener {
            Toast.makeText(this, "button2", Toast.LENGTH_SHORT).show()
        }

        binding.button3.setOnClickListener {
            Toast.makeText(this, "button3", Toast.LENGTH_SHORT).show()
        }

    }
}