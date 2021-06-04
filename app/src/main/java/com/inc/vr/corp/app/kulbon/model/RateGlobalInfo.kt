package com.inc.vr.corp.app.kulbo

import com.google.gson.annotations.SerializedName


data class RateGlobalInfo(

        @SerializedName("food_id") var foodId : String,
        @SerializedName("total") var total : Int,
        @SerializedName("average") var average : String

)