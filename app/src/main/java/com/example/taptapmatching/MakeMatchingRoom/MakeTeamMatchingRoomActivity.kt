package com.example.taptapmatching.MakeMatchingRoom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.taptapmatching.MatchType
import com.example.taptapmatching.databinding.ActivityMakeTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.MakematchingroomlayoutBinding
import com.example.taptapmatching.matchingMain.CalendarSelectFragment
import java.io.Serializable

class MakeTeamMatchingRoomActivity : AppCompatActivity() {

    val binding by lazy { MakematchingroomlayoutBinding.inflate(layoutInflater) }

    var buttons = mutableListOf<Button>()

    enum class SelectedButton(val value: Int) {
        SIX(0),
        ELEVEN(1),
        MAN(2),
        GIRL(3),
        MANANDGIRL(4),
        FOOTSALSHOES(5),
        SOCCERSHOES(6)
    }

    fun make(value: Int): SelectedButton {
        var result = SelectedButton.SIX
        when (value) {
            0 -> result = SelectedButton.SIX
            1 -> result = SelectedButton.ELEVEN
            2 -> result = SelectedButton.MAN
            3 -> result = SelectedButton.GIRL
            4 -> result = SelectedButton.MANANDGIRL
            5 -> result = SelectedButton.FOOTSALSHOES
            6 -> result = SelectedButton.SOCCERSHOES
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(binding.root)
        this.setupButtons()
        this.setupDataAndTimeButtonListener()
        this.setupPlaceSelectButtonListener()
        this.setupSelectedButtonsListener()
        this.setupBeforeMatchInfoButtonListener()
        this.setupNextButtonListener()
    }

    fun setupDataAndTimeButtonListener() {
        binding.dateAndTimeSelectButton.setOnClickListener {
            val calendarSelectFragment = CalendarSelectFragment()
            calendarSelectFragment.show(supportFragmentManager, "CalendarSelectFragment")
        }
    }

    fun setupPlaceSelectButtonListener() {
        binding.placeSelectButton.setOnClickListener {
            val placeSelectFragment = PlaceSelectFragment()
            placeSelectFragment.show(supportFragmentManager, "PlaceSelectFragment")
        }
    }

    fun setupBeforeMatchInfoButtonListener() {
        binding.beforeMatchInfoButton.setOnClickListener {
            val beforeMatchInfoFragment = BeforeMatchInfoFragment()
            beforeMatchInfoFragment.show(supportFragmentManager, "BeforeMatchInfoFragment")
        }
    }

    fun setupButtons() {
        this.buttons = mutableListOf(
                this.binding.selectButton1, this.binding.selectButton2,
                this.binding.selectButton3, this.binding.selectButton4,
                this.binding.selectButton5, this.binding.selectButton6,
                this.binding.selectButton7
        )
    }

    fun setupSelectedButtonsListener() {
        this.buttons.map {
            it.setOnClickListener {
                it.setSelected(!it.isSelected)
            }
        }

        val iterator = this.buttons.iterator()

        for ((index, value) in iterator.withIndex()) {
            value.setTag(index)
        }
    }

    fun setupNextButtonListener() {
        this.binding.nextButtonFromMakeRoom.setOnClickListener {
            //TODO: 다음 Activity를 생성한다.
            val intent = Intent(this, MakeSecondTeamMatchingRoomActivity::class.java)
            //정보를 모아서 함께 넘긴다.
            intent.putExtra("data", 5)
            //matchingListIntent.putExtra("data", matchingType as Serializable)

            this.startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val selectedButtons = this.buttons.filter { it.isSelected }.map { this.make(it.tag as Int) }
        Toast.makeText(this, "${selectedButtons}", Toast.LENGTH_SHORT).show()
    }
}

