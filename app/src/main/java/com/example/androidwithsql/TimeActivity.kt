package com.example.androidwithsql

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.databinding.ActivityMainBinding
import com.example.androidwithsql.databinding.ActivityTimeBinding
import kotlinx.android.synthetic.main.activity_time.*

class TimeActivity : AppCompatActivity(){

    lateinit var binding : ActivityTimeBinding
    lateinit var helper: SqliteHelper
    lateinit var homeIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)
        homeIntent = Intent(this,HomeActivity::class.java)

        with(binding){

            button3.setOnClickListener {
                helper.updateTime(TimeData(60,1500))
                startActivity(homeIntent)
            }

            button3.setOnClickListener {
                helper.updateTime(TimeData(180,1500))
                startActivity(homeIntent)
            }
        }
    }
}