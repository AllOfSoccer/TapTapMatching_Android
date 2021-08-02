package com.example.taptapmatching

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.taptapmatching.databinding.ActivityButton1Binding

class Button1Activity : AppCompatActivity() {

    val binding by lazy { ActivityButton1Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
//        setContentView(R.layout.activity_button1)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = "${intent.getIntExtra("from2", 0)}"

        binding.to1.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("returnValue", binding.editMessage.text.toString())
            setResult(RESULT_OK, returnIntent) //setResult 메소드에 담아서 실행 --> 호출한측으로 전달.
            finish() // 서브 액티비티가 종료된다.
        }

        Toast.makeText(this, "button1", Toast.LENGTH_SHORT).show()
    }
}