package com.foysal.wstech.dbconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.foysal.wstech.dbconnect.Common.Common
import com.foysal.wstech.dbconnect.Model.APIResponse
import com.foysal.wstech.dbconnect.Remote.IMyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var txt_register : TextView
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var btn_login : Button


    internal lateinit var mService : IMyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.api

        txt_register = findViewById(R.id.txt_register)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btn_login = findViewById(R.id.btn_login)

        txt_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_login.setOnClickListener {
            authenticateUser(email.text.toString(), password.text.toString())
        }
    }

    private fun authenticateUser(email: String, password: String) {
        mService.loginUser(email, password)
            .enqueue(object : Callback<APIResponse> {
                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error)
                        Toast.makeText(this@MainActivity, response.body()!!.error_msg,
                            Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this@MainActivity, "Login Successful", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }

            })
    }
}