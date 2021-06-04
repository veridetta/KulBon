package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbo.CatInfo
import com.inc.vr.corp.app.kulbo.RateGlobalInfo
import com.inc.vr.corp.app.kulbo.RateInfo
import com.inc.vr.corp.app.kulbo.RateListInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface RateListApi {
    @Headers("Content-Type: application/json")
    @POST("rating")
    fun loadBuku( @Body userData: RateInfo): Call<List<RateListInfo>>
}