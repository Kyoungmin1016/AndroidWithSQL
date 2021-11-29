package com.example.androidwithsql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.databinding.ItemOrderBinding

class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder>() {

    var orderDataList = mutableListOf<OrderData>()

    inner class ViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(orderData:OrderData){
            binding.orderText.text = orderData.goodsName
            binding.priceText.text = orderData.G_price.toString()
            if(isEmployee(U_id.toString()))
                binding.orderCheckBox.visibility = View.VISIBLE
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

    fun isEmployee(Employee: String): Boolean {
        return Employee.equals("Employee")
    }
}