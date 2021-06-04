package com.inc.vr.corp.app.kulbon.api

import com.inc.vr.corp.app.kulbo.RateGlobalInfo
import com.inc.vr.corp.app.kulbon.model.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiService {
    fun addUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RegisterApi::class.java)
        retrofit.addUser(userData).enqueue(
                object : Callback<UserInfo> {
                    override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                        onResult(null)
                    }

                    override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                        val addedUser = response.body()
                        onResult(addedUser)
                    }
                }
        )
    }
    fun loginUser(userData: UserInfo, onResult: (UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(LoginApi::class.java)
        retrofit.loginUser(userData).enqueue(
                object : Callback<UserInfo> {
                    override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                        onResult(null)
                    }

                    override fun onResponse(call: Call<UserInfo>, response: Response<UserInfo>) {
                        val addedUser = response.body()
                        onResult(addedUser)
                    }
                }
        )
    }
    fun getReviewGlobal(userData: RateGlobalInfo, onResult: (RateGlobalInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RateGlobalApi::class.java)
        retrofit.loadBuku(userData).enqueue(
                object : Callback<RateGlobalInfo> {
                    override fun onFailure(call: Call<RateGlobalInfo>, t: Throwable) {
                        onResult(null)
                    }

                    override fun onResponse(call: Call<RateGlobalInfo>, response: Response<RateGlobalInfo>) {
                        val addedUser = response.body()
                        onResult(addedUser)
                    }
                }
        )
    }

}