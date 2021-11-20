package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidwithsql.databinding.ActivityMainBinding
import com.example.androidwithsql.databinding.ActivitySignupBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding //메인엑티비티와 연결
    private lateinit var loginIntent : Intent   //로그인액티비티 연결

    companion object{
        val DB_MEMBER = "DB_member.sql" //SQL 이름
        val DB_VERSION = 1  //SQl 버전

        val TB_MEMBER = "TableMember"   //테이블 Member
        val TB_GOODS = "TableGoods" //테이블 Goods

        val LOGINLOG = "log_login"  //로그
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginIntent = Intent(this,LoginActivity::class.java)

        //클릭 시 로그인화면으로 이동
        binding.userModeButton.setOnClickListener {
            startActivity(loginIntent)
        }
    }
}