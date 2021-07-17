package com.example.taptapmatching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taptapmatching.databinding.ActivityRecyclerViewBinding
import com.example.taptapmatching.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class RecyclerViewActivity : AppCompatActivity() {

    val binding by lazy { ActivityRecyclerViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data: MutableList<Memo> = loadData()
        var adapter = CustomAdapter()
        adapter.listData = data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun loadData(): MutableList<Memo> {
        val data: MutableList<Memo> = mutableListOf() // 컬렉션을 선언

        for (no in 1..100) {
            val title = "이것이 안드로이드다 ${no}"
            val date = System.currentTimeMillis()
            var memo = Memo(no, title, date)
            data.add(memo)
        }

        return data
    }

    //Adapter가 하는 역할은?
    //RecyclerView.Adapter<Holder>
    // listData가 있다, 이 데이터로 itemCount값을, 해당 위치에대항하는 데이터를 생성.
    // 개별 데이터에 대응하는 뷰 홀더 클래스를 사용. (뷰 홀더는 개별 데이터를 대응)
    class CustomAdapter: RecyclerView.Adapter<Holder>() {

        var listData = mutableListOf<Memo>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),
                                                      parent, false)
            return Holder(binding)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

        //생성된 뷰 홀더를 화면에 보여줌
        override fun onBindViewHolder(holder: Holder, position: Int) {
            val memo = listData.get(position)
            holder.setMemo(memo)
        }
    }

    //Holder가 하는 역할은?
    // 개별 데이터에 대응.
    // 화면에 보이는 개수의 홀더만 생성. (위쪽의 홀더를 계속해서 재사용한다)
    class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        // binding을 생성 시점에 전달받아 해당 바인딩에 있는 데이터를 세팅
        fun setMemo(memo: Memo) {
            binding.textNo.text = "${memo.no}"

            binding.textTitle.text = memo.title

            var sdf = SimpleDateFormat("yyyy/MM/dd")
            var formattedDate = sdf.format(memo.timestamp)
            binding.textDate.text = formattedDate
        }
    }
}