package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.SqliteHelper.Companion.U_name
import com.example.androidwithsql.SqliteHelper.Companion.U_time
import com.example.androidwithsql.databinding.ActivityHomeBinding
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameText.text = U_name
        binding.timeText.text = getTimeStringFromInt(U_time)

    }

    private fun getTimeStringFromInt(time: Int): String { //시간을 스트링으로 변환
        val hours = time % 1440 / 60
        val minutes = time % 60

        return makeTimeString(hours, minutes)
    }

    private fun makeTimeString(hours: Int, minutes: Int): String = //문자 합치기
        String.format("%02d:%02d", hours, minutes)

}