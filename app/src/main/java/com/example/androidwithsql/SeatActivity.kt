package com.example.androidwithsql

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_seat.*

class SeatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)

        val seatList = generateDummyList(50)

        recycler_view.adapter = SeatAdapter(seatList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size: Int):List<Seat> {
        val list = ArrayList<Seat>()

        for (i in 1 until size+1){
            val drawable = R.drawable.ic_computer

            val item = Seat(drawable, i, "사용여부")
            list += item
        }
        return list
    }
}