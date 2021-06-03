package com.inc.vr.corp.app.kulbo

import com.google.gson.annotations.SerializedName


data class CatInfo(

    @SerializedName("id") var id: Int?,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("updated_at") var updatedAt: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("cover") var cover: String?

)