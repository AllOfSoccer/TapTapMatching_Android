package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.taptapmatching.databinding.ActivityRecyclerViewBinding
import com.example.taptapmatching.databinding.ActivityRegisterIdBinding

class RegisterIdActivity : AppCompatActivity() {

    val binding by lazy { ActivityRegisterIdBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        this.binding.requestRegisterId.setOnClickListener {
            //아이디 등록 요청.
            Log.d("RegisterId", "RequestRegisterId")
        }
    }
}