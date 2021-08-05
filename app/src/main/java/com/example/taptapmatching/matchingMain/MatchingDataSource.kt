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

        val temp = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)
        val temp2 = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)
        val temp3 = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)
        val temp4 = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)
        val temp5 = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)
        val temp6 = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)
        val temp7 = MatchingData(LocalDate.now(), "용산 더 베이스", 6, Gender.FEMALE, "토토", true, true)

        result.add(temp)
        result.add(temp2)
        result.add(temp3)
        result.add(temp4)
        result.add(temp5)
        result.add(temp6)
        result.add(temp7)

        list = result
    }

}