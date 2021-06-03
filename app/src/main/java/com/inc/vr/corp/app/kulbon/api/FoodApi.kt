package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbo.FoodInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface FoodApi {
    @Headers("Content-Type: application/json")
    @POST("food")
    fun loadBuku( @Body userData: FoodInfo): Call<List<FoodInfo>>
}