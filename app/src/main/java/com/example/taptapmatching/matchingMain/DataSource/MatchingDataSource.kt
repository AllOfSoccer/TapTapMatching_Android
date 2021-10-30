package com.example.taptapmatching.matchingMain
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import retrofit2.*
import java.io.Serializable
import java.time.LocalDate
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

class ListAPI {
    companion object {
        const val baseURL = "http://3.37.196.89:8080"
    }
}


interface Retrofit_Service {
    @Headers("tab-user-id: 2")
    @GET("${ListAPI.baseURL}/api/v1/tabtab/post/monthly?yyyyMM=202108")
    fun getList(): Call<test1>

    @POST("${ListAPI.baseURL}/api/v1/tabtab/user/add")
    fun requestRegister(@Header("secret") secret: String,
                        @Body req: TabUser
    ): Call<ApiResponse<TabUser>>

    @POST("${ListAPI.baseURL}/api/v1/tabtab/user")
    fun requestFixRegister(@Header("secret") secret: String,
                           @Body req: UserFix
    ): Call<ApiResponse<UserFix>>

    @GET("${ListAPI.baseURL}/api/v1/tabtab/user/{id}") //id도 넣어아햠.
    fun requestUserDetailInfo(@Header("secret") secret: String,
                              @Path("id") id: Int
    ): Call<ApiResponse<UserDetail>>
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

@RequiresApi(Build.VERSION_CODES.O)
class MatchingDataSource {

    var list: MutableList<MatchingData> = mutableListOf()

    companion object {
        val shared = MatchingDataSource()
    }

    //게시글 조
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

    // 회원가
    fun registerId() {
        val call = ListClient.service
        val reqBody = TabUser( id=null,"1","2","3")

        call.requestRegister(secret = "testgogo2", req = reqBody).enqueue(object: Callback<ApiResponse<TabUser>> {

            override fun onResponse(
                call: Call<ApiResponse<TabUser>>,
                response: Response<ApiResponse<TabUser>>
            ) {
                Log.d("APITest", "requestTest onResponse ${response.isSuccessful}" +
                        "\n ${response.body()}")
            }

            override fun onFailure(call: Call<ApiResponse<TabUser>>, t: Throwable) {
                Log.d("APITest", "requestTest onFailure")
            }
        })
    }

    //회원정보 수
    fun requestFix() {
        val call = ListClient.service
        val reqBody = UserFix(36,5, "z", "z", "z", "z", "z", "z", "z", 5, "z")

        call.requestFixRegister(secret = "testgogo", req = reqBody).enqueue(object: Callback<ApiResponse<UserFix>> {

            override fun onResponse(
                call: Call<ApiResponse<UserFix>>,
                response: Response<ApiResponse<UserFix>>
            ) {
                Log.d("APITest", "requestTest fix onResponse ${response.isSuccessful}" +
                        "\n ${response.body()} ${response.headers()}" +
                    "\n ${response.body()?.errorMessage} ${response.headers()}"


                )
            }

            override fun onFailure(call: Call<ApiResponse<UserFix>>, t: Throwable) {
                Log.d("APITest", "requestTest fix onFailure")
            }
        })

    }

    //회원 조회
    fun requestUserDetail() {
        val call = ListClient.service
        val reqBody = UserDetail(1, "z", "z", "z", 36, "z", "z", "z","z",5, "z")

        call.requestUserDetailInfo(secret = "testgogo", id = 36).enqueue(object: Callback<ApiResponse<UserDetail>> {

            override fun onResponse(
                call: Call<ApiResponse<UserDetail>>,
                response: Response<ApiResponse<UserDetail>>
            ) {
                Log.d("APITest", "requestTest UserDetail onResponse ${response.isSuccessful}" +
                        "\n ${response.body()} ${response.headers()}")
            }

            override fun onFailure(call: Call<ApiResponse<UserDetail>>, t: Throwable) {
                Log.d("APITest", "requestTest UserDetail onFailure")
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

data class TabUser(
    val id : Long? = null,
    val name : String,
    val email : String,
    val phone : String,
    val createdAt: String? = null,
    val updatedAt: String? = null,
)

data class UserFix(
    val id: Int,
    val ageRange : Int,
    val displayName: String,
    val displayPhone: String,
    val email: String,
    val introduction: String,
    val name: String,
    val phone: String,
    val profileImgUrl: String,
    val skillLevel: Int,
    val teamIds: String,
)

data class UserDetail(
    val ageRange: Int,
    val displayName: String,
    val displayPhone: String,
    val email: String,
    val id: Int,
    val introduction: String,
    val name: String,
    val phone : String,
    val profileImgUrl: String,
    val skillLevel: Int,
    val teamIds: String,
)

data class ApiResponse<T> (
    val result : T,
    val resultType : String,
    val errorMessage : String? = null
)