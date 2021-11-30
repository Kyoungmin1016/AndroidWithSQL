package com.example.androidwithsql

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidwithsql.databinding.ActivitySeatBinding

class SeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySeatBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val seatList = generateDummyList(50)

        binding.recyclerView.adapter = SeatAdapter(seatList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int):List<Seat> {
        val list = ArrayList<Seat>()

        for (i in 1 until size+1){
            val drawable = R.drawable.ic_computer

            val item = Seat(drawable, "좌석번호 $i", "사용여부")
            list += item
        }
        return list
    }
}