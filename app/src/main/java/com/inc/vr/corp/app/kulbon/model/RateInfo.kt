package com.inc.vr.corp.app.kulbo

import android.text.Editable
import com.google.gson.annotations.SerializedName


data class RateInfo(
        @SerializedName("id") var id: Int?,
        @SerializedName("food_id") var food_id: Int?,
        @SerializedName("user_id") var user_id: Int?,
        @SerializedName("rating") var rating: String?,
        @SerializedName("comment") var comment: String?

)