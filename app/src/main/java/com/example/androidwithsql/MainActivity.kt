package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidwithsql.databinding.ActivityMainBinding
import com.example.androidwithsql.databinding.ActivitySignupBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding //메인엑티비티와 연결
    private lateinit var loginIntent : Intent   //로그인액티비티 연결

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        loginIntent = Intent(this,LoginActivity::class.java)

        //클릭 시 로그인화면으로 이동
        binding.UserModeButton.setOnClickListener {
            startActivity(loginIntent)
        }
    }
}