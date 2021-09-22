package com.example.taptapmatching.MakeMatchingRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.taptapmatching.databinding.ActivityMakeTeamMatchingRoomBinding
import com.example.taptapmatching.databinding.MakematchingroomlayoutBinding
import com.example.taptapmatching.matchingMain.CalendarSelectFragment

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

    override fun onDestroy() {
        super.onDestroy()

        val selectedButtons = this.buttons.filter { it.isSelected }.map { this.make(it.tag as Int) }
        Toast.makeText(this, "${selectedButtons}", Toast.LENGTH_SHORT).show()
    }
}

