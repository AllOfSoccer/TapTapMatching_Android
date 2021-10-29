package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taptapmatching.databinding.ActivityRecyclerViewBinding
import com.example.taptapmatching.databinding.ActivityRegisterIdBinding

class RegisterIdActivity : AppCompatActivity() {

    val binding by lazy { ActivityRegisterIdBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}