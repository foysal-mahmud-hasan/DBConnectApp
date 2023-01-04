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
import retrofit2.Response
import java.net.PasswordAuthentication
import javax.security.auth.callback.Callback

class RegisterActivity : AppCompatActivity() {

    private lateinit var mService : IMyApi

    lateinit var nameRegister : EditText
    lateinit var emailRegister : EditText
    lateinit var passwordRegister : EditText
    lateinit var btnRegister : Button
    lateinit var loginRegister : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mService = Common.api

        nameRegister = findViewById(R.id.name_register)
        emailRegister = findViewById(R.id.email_Register)
        passwordRegister = findViewById(R.id.password_register)
        btnRegister = findViewById(R.id.btn_register)
        loginRegister = findViewById(R.id.txt_login_register)

        loginRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnRegister.setOnClickListener {

            createNewUser(nameRegister.text.toString(), emailRegister.text.toString(),
                passwordRegister.text.toString())

        }





    }

    private fun createNewUser(name: String, email: String, password: String) {


        mService.registerUser(name, email, password)
            .enqueue(object : retrofit2.Callback<APIResponse> {
                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error)
                        Toast.makeText(this@RegisterActivity, response.body()!!.error_msg,
                            Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this@RegisterActivity, "Register Successful"
                                + response.body()!!.uid, Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
                }


            })

    }
}