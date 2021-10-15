package org.sussanacode.propertymanagementapplication.entity.response

import com.google.gson.annotations.SerializedName


data class PropertyResponseData(
    @SerializedName("count")
    val count: Int,

    @SerializedName("message")
    val message: String,

    @SerializedName("error")
    val error: Boolean,

    @SerializedName("data")
    val data: List<Property>?,

    )




data class Property (

    @SerializedName("_id")
    val _id: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("purchasePrice")
    val purchasePrice: String,

    @SerializedName("latitude")
    val latitude: String?,

    @SerializedName("longitude")
    val longitude: String?,

    @SerializedName("mortageInfo")
    val mortageInfo: Boolean?,

    @SerializedName("propertyStatus")
    val propertyStatus: Boolean?,

    @SerializedName("userId")
    val userId: String?,

    @SerializedName("userType")
    val userType: String?

)



//data class Data(
//    val __v: Int,
//    val _id: String,
//    val address: String,
//    val city: String,
//    val country: String,
//    val image: String,
//    val latitude: String,
//    val longitude: String,
//    val mortageInfo: Boolean,
//    val propertyStatus: Boolean,
//    val purchasePrice: String,
//    val state: String,
//    val userId: String,
//    val userType: String,
//
//)