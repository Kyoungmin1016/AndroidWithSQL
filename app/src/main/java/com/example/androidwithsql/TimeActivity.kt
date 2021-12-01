package com.example.androidwithsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidwithsql.databinding.ActivityMainBinding
import com.example.androidwithsql.databinding.ActivityTimeBinding
import kotlinx.android.synthetic.main.activity_time.*

class TimeActivity : AppCompatActivity(){

    lateinit var binding : ActivityTimeBinding
    lateinit var helper: SqliteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = SqliteHelper(this, MainActivity.DB_MEMBER, MainActivity.DB_VERSION)

        binding.button3.setOnClickListener {
            helper.updateTime(TimeData(180,1500))
            Toast.makeText(this,"${binding.hours3.text.toString()}시간 충전하였습니다.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }
}