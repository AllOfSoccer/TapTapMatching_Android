package com.example.taptapmatching

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import com.example.taptapmatching.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        // Intent를 만들고 다음 액티비티로 전환.
        val intent = Intent(this ,Button1Activity::class.java)
        intent.putExtra("from1", "Hello Bundle")
        intent.putExtra("from2", 2021)

        binding.button1.setOnClickListener {
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            Toast.makeText(this, "button2", Toast.LENGTH_SHORT).show()
        }

        binding.button3.setOnClickListener {
            Toast.makeText(this, "button3", Toast.LENGTH_SHORT).show()
        }

    }
}