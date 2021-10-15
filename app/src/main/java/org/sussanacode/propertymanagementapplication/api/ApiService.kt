package org.sussanacode.propertymanagementapplication.api

import org.sussanacode.propertymanagementapplication.entity.request.LoginRequestData
import org.sussanacode.propertymanagementapplication.entity.request.PropertyRequestData
import org.sussanacode.propertymanagementapplication.entity.request.RegisterRequestData
import org.sussanacode.propertymanagementapplication.entity.response.LoginResponseData
import org.sussanacode.propertymanagementapplication.entity.response.PropertyResponseData
import org.sussanacode.propertymanagementapplication.entity.response.RegisterResponseData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Content-type: application/json")
    @POST("auth/login")
    fun login(@Body requestData: LoginRequestData): Call<LoginResponseData>

    @Headers("Content-type: application/json")
    @POST("auth/register")
    fun register(@Body requestData: RegisterRequestData): Call<RegisterResponseData>


    @Headers("Content-type: application/json")
    @POST("property")
    fun postProperty(@Body propertyData: PropertyRequestData): Call<PropertyResponseData>


    @GET("property")
    fun getProperty(): Call<PropertyResponseData>

   // https://apolis-property-management.herokuapp.com/api/property/user/%2260a2d28b62e1ec001788a646%22

    @GET("property/user/{id}")
    fun getPropertyByUserID(@Path("id") id:String ): Call<PropertyResponseData>
}