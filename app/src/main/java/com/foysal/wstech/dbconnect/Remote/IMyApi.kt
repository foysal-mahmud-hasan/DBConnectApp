package com.foysal.wstech.dbconnect.Remote

import com.foysal.wstech.dbconnect.Model.APIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IMyApi {

    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(@Field("name") name : String, @Field("email") email : String,
                     @Field("password") password : String) : Call<APIResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(@Field("email") email : String,
                     @Field("password") password : String) : Call<APIResponse>

}