package com.example.taptapmatching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class smallFilteringFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 아래 라인을 binding을 사용하는 코드로 변경
        return inflater.inflate(R.layout.fragment_small_filtering, container, false)
    }

    // inflater : 레이아웃 파일을 로드가히 위한 레이아웃 인플레이터를 기본으로 제공.
    // Container: 프래그먼트 레이아웃이 배치되는 부모 레이아웃 (액티비티의 레이아웃)
    // savedInstanceState: 상태 값 저장을 위한 보조 도구. 액티비티의 onCreate의 파라미터와 동일하게 동작.
}