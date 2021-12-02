package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.databinding.ActivityTimeBinding

class TimeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTimeBinding
    private lateinit var helper : SqliteHelper
    private lateinit var homeIntent : Intent
    private lateinit var adapter : TimeRecyclerViewAdapter
    var initList = mutableListOf<TimeData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)
        homeIntent = Intent(this,HomeActivity::class.java)

        initTimeData()
        presentTimeRecyclerView()
    }

    private fun initTimeData(){
        helper.insertTimeData(TimeData(60,1500))
        helper.insertTimeData(TimeData(180,3000))
        helper.insertTimeData(TimeData(300,5000))
        helper.insertTimeData(TimeData(600,9500))
        helper.insertTimeData(TimeData(1440,20000))
    }

    private fun presentTimeRecyclerView() {
        adapter = TimeRecyclerViewAdapter() //어댑터 객체 생성
        adapter.timeDataList = helper.presentTimeData()  //데이터 삽입
        binding.timeRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.timeRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
    }
}