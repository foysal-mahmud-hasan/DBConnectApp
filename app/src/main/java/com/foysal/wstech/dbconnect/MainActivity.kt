package com.foysal.wstech.dbconnect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foysal.wstech.dbconnect.Common.Common
import com.foysal.wstech.dbconnect.Remote.IMyApi

class MainActivity : AppCompatActivity() {

    internal lateinit var mService : IMyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.api

        txt_register
    }
}