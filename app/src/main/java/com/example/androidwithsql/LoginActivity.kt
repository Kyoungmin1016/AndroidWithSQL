package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidwithsql.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding //로그인뷰 연결
    private lateinit var signupIntent : Intent  //회원가입액티비티와 연결




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signupIntent = Intent(this,SignupActivity::class.java)

        //클릭시 회원가입화면으로 이동
        binding.signupButton.setOnClickListener {
            startActivity(signupIntent)
        }



    }
}