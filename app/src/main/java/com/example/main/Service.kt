package com.example.main

import android.provider.ContactsContract.CommonDataKinds.Nickname
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Service {

    @POST("/api/v1/fairytale")
    fun makeFairytale(
        @Query("age-group") ageGroup: String,
        @Body taleCreateDto: TaleCreate
    ): Call<TaleResponse>


    @POST("/api/v1/fairytale/translate")
    fun translateFairytale(
        @Body translate: TransLate
    ) : Call<ResponseBody>
}