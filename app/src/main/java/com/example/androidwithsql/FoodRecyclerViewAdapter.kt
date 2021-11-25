package com.example.androidwithsql

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.MainActivity.Companion.LOG_FOOD
import com.example.androidwithsql.databinding.ItemGoodsBinding

class FoodRecyclerViewAdapter : RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder>(){

    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 GoodsFragment등에서 foodDataList에 실제 데이터 추가
    var goodsDataList = mutableListOf<GoodsData>()
    private val checkBoxStatus = SparseBooleanArray()

    inner class ViewHolder(private val binding: ItemGoodsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(foodData : GoodsData){
            with(binding){
                goodsName.text = foodData.goodsName
                GPrice.text = foodData.G_price.toString()

                goodsCheckBox.isChecked = checkBoxStatus[adapterPosition]

                goodsCheckBox.setOnClickListener {
                    if(!goodsCheckBox.isChecked)
                        checkBoxStatus.put(adapterPosition, false)
                    else
                        checkBoxStatus.put(adapterPosition, true)
                    Log.d(LOG_FOOD,"adapterPosition : ${adapterPosition} isChecked : ${goodsCheckBox.isChecked}")
                    notifyItemChanged(adapterPosition)
                }
            }
        }
    }

    //만들어진 뷰홀더 없을 때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGoodsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    //recyclerView가 viewholder를 가져와 데이터 연결할 때 호출
    //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(goodsDataList[position])
    }

    //개수 확인
    override fun getItemCount(): Int {
        return goodsDataList.size
    }
}