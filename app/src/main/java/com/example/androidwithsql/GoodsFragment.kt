package com.example.androidwithsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.databinding.FragmentGoodsBinding
import com.example.androidwithsql.databinding.ItemGoodsBinding

class GoodsFragment : AppCompatActivity() {

    private lateinit var binding : FragmentGoodsBinding
    private lateinit var adapter: FoodRecyclerViewAdapter   //클래스 FoodRecyclerViewAdapter 호출
    private lateinit var helper: SqliteHelper   //클래스 SqliteHelper 호출

    val mDatas = mutableListOf<GoodsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentGoodsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)

        //상품초기화버튼 클릭 시
        binding.initFoodButton.setOnClickListener {
            initGoodsData()
            presentGoodsRecyclerView()
        }

        //상품데이터 리사이클러뷰로 보여주기
        presentGoodsRecyclerView()
    }

    private fun initGoodsData() {
        helper.insertGoodsData(GoodsData("닭꼬치",2000,10))
        helper.insertGoodsData(GoodsData("떡꼬치",1000,10))
        helper.insertGoodsData(GoodsData("떡볶이",2000,10))
        helper.insertGoodsData(GoodsData("멘보샤",4000,10))
        helper.insertGoodsData(GoodsData("샌드위치",3000,10))
        helper.insertGoodsData(GoodsData("치킨",7000,10))
        helper.insertGoodsData(GoodsData("햄버거",3000,10))

    }

    private fun presentGoodsRecyclerView() {
        adapter = FoodRecyclerViewAdapter() //어댑터 객체 생성
        adapter.goodsDataList = helper.presentGoodsData()   //데이터 삽입
        binding.goodsRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.goodsRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
    }

}