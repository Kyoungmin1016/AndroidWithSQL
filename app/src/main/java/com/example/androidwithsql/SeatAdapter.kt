package com.example.androidwithsql

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.databinding.ItemSeatBinding

class SeatAdapter : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    var seatList = mutableListOf<SeatData>()
    private lateinit var helper: SqliteHelper

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val binding = ItemSeatBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        helper = SqliteHelper(binding.root.context, MainActivity.DB_MEMBER, MainActivity.DB_VERSION)
        return SeatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(seatList[position])
    }

    override fun getItemCount() = seatList.size

    inner class SeatViewHolder(private val binding : ItemSeatBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(seatData : SeatData){
            binding.imageView.setImageResource(seatData.imageResource)
            binding.seatNoTextView.text = seatData.seatNo.toString()
            binding.usedSeatText.text = seatData.M_id

            binding.selectSeatButton.setOnClickListener {
                if(seatData.M_id.toString() != "사용 가능"){
                    Toast.makeText(binding.root.context,"${seatData.seatNo}번은 사용중입니다.",Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(binding.root.context, "${seatData.seatNo}번 좌석을 선택하셨습니다.", Toast.LENGTH_SHORT).show()
                    helper.updateSeatData(seatData)
                    ContextCompat.startActivity(
                        binding.root.context,
                        Intent(binding.root.context, TimeActivity::class.java),
                        null
                    )
                }
            }
        }

    }
}