package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbo.CatInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

public interface CatApi {
    @Headers("Content-Type: application/json")
    @POST("book")
    fun loadBuku( @Body userData: CatInfo): Call<List<CatInfo>>
}