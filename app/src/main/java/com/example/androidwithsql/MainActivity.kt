package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidwithsql.databinding.ActivityMainBinding
import com.example.androidwithsql.databinding.ActivitySignupBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding //메인엑티비티와 연결
    private lateinit var loginIntent : Intent   //로그인액티비티 연결
    private lateinit var homeIntent : Intent
    private lateinit var helper: SqliteHelper

    companion object{
        val DB_MEMBER = "DB_member.sql" //SQL 이름
        val DB_VERSION = 1  //SQl 버전

        val TB_MEMBER = "TableMember"   //테이블 Member
        val TB_GOODS = "TableGoods" //테이블 Goods

        val LOG_LOGIN = "Log_login"  //로그
        val LOG_TIMER = "Log_timer"
        val LOG_FOOD = "Log_food"
        val LOG_ORDER = "Log_order"
        val LOG_PRICE = "Log_price"
        val LOG_EMPLOYEE = "Log_employee"
        val LOG_SEAT = "Log_seat"
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)
        loginIntent = Intent(this,LoginActivity::class.java)
        homeIntent = Intent(this,HomeActivity::class.java)

        //클릭 시 로그인화면으로 이동
        binding.userModeButton.setOnClickListener {
            startActivity(loginIntent)
        }

        binding.empModeButton.setOnClickListener {
            helper.insertMemberData(MemberData("Employee","Employee","직원",null,180))
            helper.checkMemberData("Employee","Employee")
            startActivity(homeIntent)
        }
    }
}