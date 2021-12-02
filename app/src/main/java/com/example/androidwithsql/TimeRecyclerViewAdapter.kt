package com.example.androidwithsql

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.databinding.ItemTimeBinding

class TimeRecyclerViewAdapter : RecyclerView.Adapter<TimeRecyclerViewAdapter.ViewHolder>(){

    var timeDataList = mutableListOf<TimeData>()
    private lateinit var helper: SqliteHelper

    inner class ViewHolder(private val binding: ItemTimeBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(timeData:TimeData){
            helper = SqliteHelper(binding.root.context,DB_MEMBER, DB_VERSION)

            with(binding){
                saleTimeText.text = getTimeStringFromInt(timeData.time)
                saleTimePriceText.text = timeData.T_price.toString()


                selectedTimeButton.setOnClickListener {
                    helper.updateTimeData(timeData)
                    ContextCompat.startActivity(binding.root.context, Intent(binding.root.context,HomeActivity::class.java),null)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeRecyclerViewAdapter.ViewHolder {
        val binding = ItemTimeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(timeDataList[position])
    }

    override fun getItemCount(): Int {
        return timeDataList.size
    }

    //시간을 스트링으로 변환
    private fun getTimeStringFromInt(time: Int): String {
        val hours = time / 60
        val minutes = time % 60

        return makeTimeString(hours, minutes)
    }

    //문자 합치기
    private fun makeTimeString(hours: Int, minutes: Int): String = String.format("%02d:%02d", hours, minutes)
}