package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding //로그인뷰 연결
    private lateinit var signupIntent : Intent  //회원가입액티비티와 연결
    private lateinit var helper: SqliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signupIntent = Intent(this,SignupActivity::class.java)
        helper = SqliteHelper(this,DB_MEMBER,DB_VERSION)

        //클릭시 회원가입화면으로 이동
        binding.signupButton.setOnClickListener {
            startActivity(signupIntent)
        }

        binding.loginButton.setOnClickListener {
            if(binding.longinId.text != null && binding.loginPw.text != null){
                if(helper.checkMemberData(binding.longinId.toString(),binding.loginPw.toString())){
                    Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"로그인 실패",Toast.LENGTH_SHORT).show()
                }
            }
            else
                Toast.makeText(this,"아이디 또는 비밀번호가 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()
        }



    }
}