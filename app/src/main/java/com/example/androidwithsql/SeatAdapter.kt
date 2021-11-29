package com.example.androidwithsql

import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class SeatAdapter(private val SeatList: List<Seat>) :
    RecyclerView.Adapter<SeatAdapter.SeatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
        parent, false)

        return SeatViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val currentItem = SeatList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.seatNo.toString()
        holder.textView2.text = currentItem.M_id
    }

    override fun getItemCount() = SeatList.size

    inner class SeatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2

    }
}