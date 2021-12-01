package com.example.androidwithsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidwithsql.MainActivity.Companion.DB_MEMBER
import com.example.androidwithsql.MainActivity.Companion.DB_VERSION
import com.example.androidwithsql.MainActivity.Companion.LOG_ORDER
import com.example.androidwithsql.MainActivity.Companion.LOG_TIMER
import com.example.androidwithsql.SqliteHelper.Companion.U_id
import com.example.androidwithsql.databinding.ActivityHomeBinding
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var GoodsFragmentIntent : Intent
    private lateinit var helper: SqliteHelper
    private lateinit var adapter: OrderRecyclerViewAdapter   //클래스 FoodRecyclerViewAdapter 호출
    private lateinit var memberData: MemberData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        helper = SqliteHelper(this, DB_MEMBER, DB_VERSION)
        GoodsFragmentIntent = Intent(this,GoodsFragment::class.java)
        adapter = OrderRecyclerViewAdapter() //어댑터 객체 생성

        memberData = helper.getMemberData(U_id.toString())

        //초기설정
        binding.nameText.text = memberData.name

        with(binding){
            //직원일때
            if(adapter.isEmployee(U_id.toString())) {
                timeText.visibility = View.INVISIBLE
                summedPriceView.visibility = View.GONE
                orderCompleteButton.visibility = View.VISIBLE
            }else{
                //멤버일때
                timeText.visibility = View.VISIBLE
                summedPriceView.visibility = View.VISIBLE
                orderCompleteButton.visibility = View.INVISIBLE
            }
            timeText.text = getTimeStringFromInt(memberData.time)
            summedPriceView.text = "총 가격 : ${helper.presentSummedPrice(U_id.toString())}"

            //상품버튼클릭시 화면이동
            GoodsButton.setOnClickListener {
                startActivity(GoodsFragmentIntent)
            }

            //주문완료버튼 클릭시 주문투플 삭제
            orderCompleteButton.setOnClickListener {
                for(i in 0 until adapter.tempOrderItemData.size){
                    Log.d(LOG_ORDER,"tempOrderItemData[${i}] : ${adapter.tempOrderItemData.get(index = i)}")
                    helper.completeOrder(adapter.tempOrderItemData.get(index = i))
                }
                presentOrderRecyclerView()
            }
        }
        presentOrderRecyclerView()
    }

    private fun presentOrderRecyclerView() {
        adapter.orderDataList = helper.presentCustemerOrder(U_id.toString())   //데이터 삽입
        Log.d("log_what","orderDataList : ${adapter.orderDataList}")
        binding.orderRecyclerView.adapter = adapter //리사이클러뷰에 어댑터 연결
        binding.orderRecyclerView.layoutManager = LinearLayoutManager(this) //레이아웃 매니저 연결
    }


    //시간을 스트링으로 변환
    private fun getTimeStringFromInt(time: Int): String {
        val hours = time % 1440 / 60
        val minutes = time % 60

        return makeTimeString(hours, minutes)
    }

    //문자 합치기
    private fun makeTimeString(hours: Int, minutes: Int): String = String.format("%02d:%02d", hours, minutes)

}