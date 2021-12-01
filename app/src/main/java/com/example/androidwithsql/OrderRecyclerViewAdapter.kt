package com.example.androidwithsql

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.MainActivity.Companion.LOG_EMPLOYEE
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.databinding.ItemOrderBinding

class OrderRecyclerViewAdapter : RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder>() {

    var orderDataList = mutableListOf<OrderData>()
    private val checkBoxStatus = SparseBooleanArray()
    val tempOrderItemData = mutableListOf<OrderData>()

    inner class ViewHolder(private val binding: ItemOrderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(orderData:OrderData){
            with(binding){
                orderText.text = orderData.goodsName
                orderNameText.text = orderData.M_id
                orderSeatText.text = orderData.seatNo.toString()
                priceText.text = orderData.G_price.toString()

                //직원일때
                if(isEmployee(U_id.toString())) {
                    orderCheckBox.visibility = View.VISIBLE
                    orderNameText.visibility = View.VISIBLE
                    orderSeatText.visibility = View.VISIBLE
                }else{
                    //사용자일 때
                    orderCheckBox.visibility = View.INVISIBLE
                    orderNameText.visibility = View.INVISIBLE
                    orderSeatText.visibility = View.INVISIBLE
                }

                //체크박스 오류 방지 및 체크박스데이터 저장
                orderCheckBox.isChecked = checkBoxStatus[adapterPosition]
                orderCheckBox.setOnClickListener {
                    if(!orderCheckBox.isChecked) {
                        checkBoxStatus.put(adapterPosition, false)
                        tempOrderItemData.remove(OrderData(orderDataList[position].orderNo,orderDataList[position].goodsName,orderDataList[position].M_id,null,null))
                    }
                    else {
                        checkBoxStatus.put(adapterPosition, true)
                        tempOrderItemData.add(OrderData(orderDataList[position].orderNo,orderDataList[position].goodsName,orderDataList[position].M_id,null,null))
                    }
                    Log.d(LOG_EMPLOYEE,"adapterPosition : ${adapterPosition} isChecked : ${orderCheckBox.isChecked}")
                    Log.d(LOG_EMPLOYEE,"tempOrderItemData : ${tempOrderItemData}")
                    notifyItemChanged(adapterPosition)
                }
            }
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