package com.example.androidwithsql

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidwithsql.databinding.ActivitySeatBinding
import kotlinx.android.synthetic.main.fragment_goods.*

class SeatActivity : AppCompatActivity() {

    private lateinit var adapter: SeatAdapter
    private lateinit var helper: SqliteHelper
    private lateinit var binding : ActivitySeatBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySeatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SeatAdapter()
        helper = SqliteHelper(this, MainActivity.DB_MEMBER, MainActivity.DB_VERSION)

        binding.initSeatButton.setOnClickListener {
            initSeatData(10)
            presentSeatRecyclerView()
            binding.initSeatButton.visibility = View.GONE
        }

        presentSeatRecyclerView()

    }

    private fun initSeatData(size : Int) {
        for(i in 1..size){
            helper.insertSeat()
        }
    }

    private fun presentSeatRecyclerView(){
        adapter.seatList = helper.presentSeatData() //어댑터에 전달
        binding.seatRecyclerView.adapter = adapter
        binding.seatRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.seatRecyclerView.setHasFixedSize(true)
    }
}