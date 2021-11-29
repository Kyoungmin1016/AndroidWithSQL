package com.example.androidwithsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.databinding.ItemGoodsBinding
import com.example.androidwithsql.databinding.ItemOrderBinding

class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder>() {

    var orderDataList = mutableListOf<OrderData>()

    inner class ViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(orderData:OrderData){
            binding.orderText.text = orderData.goodsName
            binding.priceText.text = orderData.G_price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderRecyclerViewAdapter.ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(orderDataList[position])
    }

    override fun getItemCount(): Int {
        return orderDataList.size
    }

}