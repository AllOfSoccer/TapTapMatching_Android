package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button1)
        button1.setOnClickListener {
            Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show()
        }

        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            Toast.makeText(this, "button2", Toast.LENGTH_SHORT).show()
        }

        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener {
            Toast.makeText(this, "button3", Toast.LENGTH_SHORT).show()
        }

    }
}