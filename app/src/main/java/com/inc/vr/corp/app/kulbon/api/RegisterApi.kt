package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbon.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterApi {
    @Headers("Content-Type: application/json")
    @POST("registerApp")
    fun addUser(@Body userData: UserInfo): Call<UserInfo>
}