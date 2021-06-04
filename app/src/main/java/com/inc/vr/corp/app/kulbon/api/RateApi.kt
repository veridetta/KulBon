package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbo.RateGlobalInfo
import com.inc.vr.corp.app.kulbo.RateInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface RateApi {
    @Headers("Content-Type: application/json")
    @POST("rating/add")
    fun loadBuku( @Body userData: RateInfo): Call<List<RateInfo>>
}