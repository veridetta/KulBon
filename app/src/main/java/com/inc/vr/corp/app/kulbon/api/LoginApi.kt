package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbon.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    @Headers("Content-Type: application/json")
    @POST("loginApp")
    fun loginUser(@Body userData: UserInfo): Call<UserInfo>
}