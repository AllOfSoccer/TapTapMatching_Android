package com.example.taptapmatching.matchingMain

import java.time.LocalDate

enum class Gender {
    FEMALE,
    MALE;
}

data class MatchingData(val time: LocalDate,
                        val location: String,
                        val numberOfPerson: Int,
                        val gender: Gender,
                        val teamName: String,
                        val isFavorite: Boolean,
                        val isFindNow: Boolean) {
}

class MatchingDataSource {

    var list: MutableList<MatchingData> = mutableListOf()

    companion object {
        val shared = MatchingDataSource()
    }

    init {
        var result: MutableList<MatchingData> = mutableListOf()

        val temp = MatchingData(LocalDate.now(), "용산 더 베이스1", 6, Gender.FEMALE, "토토", true, true)
        val temp2 = MatchingData(LocalDate.now(), "용산 더 베이스2", 6, Gender.FEMALE, "토토", true, true)
        val temp3 = MatchingData(LocalDate.now(), "용산 더 베이스3", 6, Gender.FEMALE, "토토", true, true)
        val temp4 = MatchingData(LocalDate.now(), "용산 더 베이스4", 6, Gender.FEMALE, "토토", true, true)
        val temp5 = MatchingData(LocalDate.now(), "용산 더 베이스5", 6, Gender.FEMALE, "토토", true, true)
        val temp6 = MatchingData(LocalDate.now(), "용산 더 베이스6", 6, Gender.FEMALE, "토토", true, true)
        val temp7 = MatchingData(LocalDate.now(), "용산 더 베이스7", 6, Gender.FEMALE, "토토", true, true)
        val temp8 = MatchingData(LocalDate.now(), "용산 더 베이스8", 6, Gender.FEMALE, "토토", true, true)
        val temp9 = MatchingData(LocalDate.now(), "용산 더 베이스9", 6, Gender.FEMALE, "토토", true, true)
        val temp10 = MatchingData(LocalDate.now(), "용산 더 베이스10", 6, Gender.FEMALE, "토토", true, true)
        val temp11 = MatchingData(LocalDate.now(), "용산 더 베이스11", 6, Gender.FEMALE, "토토", true, true)

        result.add(temp)
        result.add(temp2)
        result.add(temp3)
        result.add(temp4)
        result.add(temp5)
        result.add(temp6)
        result.add(temp7)
        result.add(temp8)
        result.add(temp9)
        result.add(temp10)
        result.add(temp11)

        list = result
    }

}