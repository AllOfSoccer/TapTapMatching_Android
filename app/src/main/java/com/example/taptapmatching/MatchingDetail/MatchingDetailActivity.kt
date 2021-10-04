package com.example.taptapmatching.MatchingDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.taptapmatching.databinding.ActivityMatchingDetailBinding
import com.naver.maps.map.MapView
import com.example.taptapmatching.databinding.ActivityMatchingListViewBinding
import com.naver.maps.map.NaverMap

class MatchingDetailActivity : AppCompatActivity() {

    val binding by lazy { ActivityMatchingDetailBinding.inflate(layoutInflater) }

    private lateinit var mapView: com.naver.maps.map.MapView

    //설마 바인딩이 안먹히는 것인가?
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        mapView = findViewById<com.naver.maps.map.MapView>(R.id.map_view)
        this.setContentView(binding.root)

        binding.mapView.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()

        binding.mapView.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)

        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()

        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()

        binding.mapView.onLowMemory()
    }

}