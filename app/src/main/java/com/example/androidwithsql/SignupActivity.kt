package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidwithsql.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding    //회원가입뷰와 연결
    private lateinit var loginIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_signup)

        loginIntent = Intent(this,LoginActivity::class.java)

        binding.EnrollmentButton.setOnClickListener {
            Toast.makeText(this, "회원등록하셨습니다.", Toast.LENGTH_SHORT).show()

            startActivity(loginIntent)
        }
    }
}