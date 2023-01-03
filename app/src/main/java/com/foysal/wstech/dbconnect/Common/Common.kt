package com.foysal.wstech.dbconnect.Common

import com.foysal.wstech.dbconnect.Model.RetrofitClient
import com.foysal.wstech.dbconnect.Remote.IMyApi

object Common {

    val BASE_URL = "http://10.0.2.2/myApi/"

    val api:IMyApi
    get() = RetrofitClient.getClient(BASE_URL).create(IMyApi::class.java)

}