package com.inc.vr.corp.app.kulbo

import android.text.Editable
import com.google.gson.annotations.SerializedName
import com.inc.vr.corp.app.kulbon.model.UserInfo


data class RateListInfo (

        @SerializedName("id") var id : Int,
        @SerializedName("created_at") var createdAt : String,
        @SerializedName("updated_at") var updatedAt : String,
        @SerializedName("comment") var comment : String,
        @SerializedName("rating") var rating : Int,
        @SerializedName("food_id") var foodId : Int,
        @SerializedName("user_id") var userId : Int,
        @SerializedName("users") var users : UserInfo,
)