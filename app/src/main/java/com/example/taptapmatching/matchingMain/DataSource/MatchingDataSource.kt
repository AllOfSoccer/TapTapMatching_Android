package com.example.taptapmatching.matchingMain
import android.util.Log
import java.io.Serializable
import java.time.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

class ListAPI {
    companion object {
        const val baseURL = "http://3.37.196.89:8080"
    }
}


interface Retrofit_Service {
    @Headers("tab-user-id: 2")
    @GET("${ListAPI.baseURL}/api/v1/tabtab/post/monthly?yyyyMM=202108")

    fun getList(): Call<test1>
}

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
                        val isFindNow: Boolean): Serializable {
}

object ListClient {
    private val client: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(ListAPI.baseURL).addConverterFactory(GsonConverterFactory.create())
    }

    val service: Retrofit_Service by lazy {
        client.build().create(Retrofit_Service::class.java)
    }
}

class MatchingDataSource {

    var list: MutableList<MatchingData> = mutableListOf()

    companion object {
        val shared = MatchingDataSource()
    }

    fun getData(myCallback: (callBackValue: MutableList<MatchingData>) -> Unit) {
        val call = ListClient.service
        var resultValue: MutableList<MatchingData> = mutableListOf()

        call.getList().enqueue(object: Callback<test1> {
            override fun onResponse(call: Call<test1>, response: Response<test1>) {
                val result = response.body()?.result?.toMutableList()
                var temp = convert(result)
                myCallback.invoke(temp)
                Log.d("APITest", "${temp}")
            }

            override fun onFailure(call: Call<test1>, t: Throwable) {
                myCallback.invoke(MatchingDataSource.shared.list)
                Log.d("APITest", "failure")
            }
        })
    }

    fun convert(list: MutableList<test2>?): MutableList<MatchingData> {
        if (list?.isNotEmpty() == true) {
            return list.map { MatchingData(LocalDate.now(), it.locationFull, it.playerCnt.toInt(), Gender.FEMALE, it.teamName, true, true) }.toMutableList()
        } else {
            return mutableListOf()
        }
    }

    init {
        var result: MutableList<MatchingData> = mutableListOf()

        val temp = MatchingData(LocalDate.now(), "실패한 경우 기본값으로 세팅된 정보", 6, Gender.FEMALE, "토토", true, true)
        val temp2 = MatchingData(LocalDate.now(), "실패한 경우 기본값으로 세팅된 정보", 6, Gender.FEMALE, "토토", true, true)
        val temp3 = MatchingData(LocalDate.now(), "실패한 경우 기본값으로 세팅된 정보", 6, Gender.FEMALE, "토토", true, true)
        val temp4 = MatchingData(LocalDate.now(), "실패한 경우 기본값으로 세팅된 정보", 6, Gender.FEMALE, "토토", true, true)
        val temp5 = MatchingData(LocalDate.now(), "실패한 경우 기본값으로 세팅된 정보", 6, Gender.FEMALE, "토토", true, true)
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

data class test1 (
    val resultType: String,
    val result: List<test2>
)

data class test2 (
    val id: Long,
    val userID: Int,
    val matchDatetime: String,
    val matchYearMonth: Long,
    val locationTitle: String,
    val locationFull: String,
    val locationX: Double,
    val locationY: Double,
    val playerCnt: Long,
    val playerGender: String,
    val shoesType: String,
    val playFee: Long,
    val uniformTop: String,
    val uniformBottom: String,
    val teamName: String,
    val phone: String,
    val ageRangeStart: Long,
    val ageRangeEnd: Long,
    val skillLevel: Long,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val isMe: Boolean
)