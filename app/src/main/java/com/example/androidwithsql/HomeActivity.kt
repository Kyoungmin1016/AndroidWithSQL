package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.MainActivity.Companion.LOG_TIMER
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.SqliteHelper.Companion.U_name
import com.example.androidwithsql.SqliteHelper.Companion.U_time
import com.example.androidwithsql.databinding.ActivityHomeBinding
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var GoodsFragmentIntent : Intent
    private lateinit var helper: SqliteHelper
    private lateinit var adapter: OrderRecyclerViewAdapter   //클래스 FoodRecyclerViewAdapter 호출

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)
        GoodsFragmentIntent = Intent(this,GoodsFragment::class.java)

        //초기설정
        binding.nameText.text = U_name
        binding.timeText.text = getTimeStringFromInt(U_time)
        binding.summedPriceView.text


        binding.GoodsButton.setOnClickListener {
            startActivity(GoodsFragmentIntent)
        }

        presentOrderRecyclerView()


    }

    private fun presentOrderRecyclerView() {
        adapter = OrderRecyclerViewAdapter() //어댑터 객체 생성
        adapter.orderDataList = helper.presentCustemerOrder()   //데이터 삽입
        binding.orderRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
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