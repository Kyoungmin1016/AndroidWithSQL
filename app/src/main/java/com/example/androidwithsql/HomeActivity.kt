package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.androidwithsql.MainActivity.Companion.LOG_TIMER
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.SqliteHelper.Companion.U_name
import com.example.androidwithsql.SqliteHelper.Companion.U_time
import com.example.androidwithsql.databinding.ActivityHomeBinding
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var GoodsFragmentIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.nameText.text = U_name
        binding.timeText.text = getTimeStringFromInt(U_time)
        GoodsFragmentIntent = Intent(this,GoodsFragment::class.java)
        binding.GoodsButton.setOnClickListener {
            startActivity(GoodsFragmentIntent)
        }


    }
    //시간을 스트링으로 변환
    private fun getTimeStringFromInt(time: Int): String {
        val hours = time % 1440 / 60
        val minutes = time % 60

        return makeTimeString(hours, minutes)
    }

    //문자 합치기
    private fun makeTimeString(hours: Int, minutes: Int): String =
        String.format("%02d:%02d", hours, minutes)


}