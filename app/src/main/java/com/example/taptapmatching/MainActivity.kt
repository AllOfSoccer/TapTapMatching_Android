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
            //startActivity(intent)
            startActivityForResult(intent, 99)
        }

        val intent2 = Intent(this, ButtonActivity2::class.java)

        binding.button2.setOnClickListener {
            startActivityForResult(intent2, 98)
        }

        val intent3 = Intent(this, RecyclerViewActivity::class.java)

        binding.button3.setOnClickListener {
            startActivityForResult(intent3, 97)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //requestCode: 호출시에 메인 액티비티에서 입력하는 코드 startActivityForResult 메서드에 인텐트와 함께 입력해서 호출한 코드를 구분.
        //resultCode: 결과 처리 후 서브 액티비티에서입력하는 코드, 앞에서 RESULT_OK를 담아서 보낸 부분.
        //data: 결과 처리 후 서브액티비티가 넘겨주는 인텐트가 담겨 있습니다.

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                99 -> {
                    val message = data?.getStringExtra("returnValue")
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}