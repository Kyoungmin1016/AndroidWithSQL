package com.example.androidwithsql

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidwithsql.databinding.ActivitySeatBinding
import kotlinx.android.synthetic.main.activity_seat.*
import kotlinx.android.synthetic.main.item.view.*

class SeatActivity : AppCompatActivity() {

    lateinit var adapter: SeatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySeatBinding.inflate(layoutInflater)
        val timeIntent = Intent(this,TimeActivity::class.java)

        setContentView(binding.root)


        adapter = SeatAdapter()
        adapter.seatList = generateDummyList(10)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)


    }

    private fun generateDummyList(size: Int): MutableList<SeatData> {
        val list = mutableListOf<SeatData>()

        for (i in 1..size){
            val drawable = R.drawable.ic_computer
            val item = SeatData(drawable, i, "사용 여부")

            list.add(item)
        }
        return list
    }
}