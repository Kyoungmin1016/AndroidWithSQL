package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.MainActivity.Companion.LOG_ORDER
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.databinding.FragmentGoodsBinding
import com.example.androidwithsql.databinding.ItemGoodsBinding

class GoodsFragment : AppCompatActivity() {

    private lateinit var binding : FragmentGoodsBinding
    private lateinit var adapter: FoodRecyclerViewAdapter   //클래스 FoodRecyclerViewAdapter 호출
    private lateinit var helper: SqliteHelper   //클래스 SqliteHelper 호출
    private lateinit var homeIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentGoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)
        homeIntent = Intent(this,HomeActivity::class.java)

        binding.initFoodButton.setOnClickListener {
            initGoodsData()
            binding.initFoodButton.visibility = View.GONE
            presentGoodsRecyclerView()
        }

        binding.OrderButton.setOnClickListener {
            for(i in 0 until adapter.tempGoodsItemData.size){
                Log.d(LOG_ORDER,"tempGoodsItemData[${i}] : ${adapter.tempGoodsItemData.get(index = i)}")
                helper.insertOrderItem(adapter.tempGoodsItemData.get(index = i))
            }
            startActivity(homeIntent)

        }

        //상품데이터 리사이클러뷰로 보여주기
        presentGoodsRecyclerView()
    }

    private fun initGoodsData() {
        with(helper){
            insertGoodsData(GoodsData("닭꼬치",2000,10,null))
            insertGoodsData(GoodsData("떡꼬치",1000,10,null))
            insertGoodsData(GoodsData("떡볶이",2000,10,null))
            insertGoodsData(GoodsData("멘보샤",4000,10,null))
            insertGoodsData(GoodsData("샌드위치",3000,10,null))
            insertGoodsData(GoodsData("치킨",7000,10,null))
            insertGoodsData(GoodsData("햄버거",3000,10,null))
        }

    }

    private fun presentGoodsRecyclerView() {
        adapter = FoodRecyclerViewAdapter() //어댑터 객체 생성
        adapter.goodsDataList = helper.presentGoodsData()   //데이터 삽입
        binding.goodsRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.goodsRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
    }


}