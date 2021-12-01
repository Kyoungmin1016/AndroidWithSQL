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
import com.example.androidwithsql.databinding.ActivityTimeBinding
import com.example.androidwithsql.databinding.ItemBinding
import kotlinx.android.synthetic.main.item.view.*

class SeatAdapter : RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    var seatList = mutableListOf<SeatData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return SeatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        holder.bind(seatList[position])
    }

    override fun getItemCount() = seatList.size

    inner class SeatViewHolder(private val binding : ItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(seatData : SeatData){
            binding.imageView.setImageResource(seatData.imageResource)
            binding.seatNoTextView.text = seatData.seatNo.toString()
            binding.usedSeatText.text = seatData.M_id.toString()

            binding.selectSeatButton.setOnClickListener {
                ContextCompat.startActivity(binding.root.context,Intent(binding.root.context,TimeActivity::class.java),null)
            }
        }

    }
}