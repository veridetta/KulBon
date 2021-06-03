package com.inc.vr.corp.app.kulbo

import com.google.gson.annotations.SerializedName


data class FoodInfo(

    @SerializedName("id") var id: Int?,
    @SerializedName("created_at") var createdAt: String?,
    @SerializedName("updated_at") var updatedAt: String?,
    @SerializedName("name") var name: String?,
    @SerializedName("address") var address: String?,
    @SerializedName("price") var price: String?,
    @SerializedName("phone") var phone: String?,
    @SerializedName("open") var open: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("facility") var facility: String?,
    @SerializedName("map") var map: String?,
    @SerializedName("gallery") var gallery: String?,
    @SerializedName("cover") var cover: String?,
    @SerializedName("cat_id") var catId: Int?

)