package com.example.androidwithsql

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.database.getIntOrNull
import com.example.androidwithsql.MainActivity.Companion.LOG_FOOD
import com.example.androidwithsql.MainActivity.Companion.LOG_LOGIN
import com.example.androidwithsql.MainActivity.Companion.LOG_ORDER


class SqliteHelper(context: Context,name: String,version:Int) : SQLiteOpenHelper(context, name, null,version) {

    companion object{
        //로그인 시 회원 아이디 및 이름,시간 임시저장
        var U_id : String? = null
        var U_name : String? = null
        var U_time : Int = 0
        var U_seat : Int? = null

        //테이블생성
        val CREATE_MEMBER =
            "create table Member(M_id varchar(20) primary key,M_password varchar(20),name varchar(20),phoneNo varchar(11),time integer)"
        val CREATE_GOODS =
            "create table Goods(goodsName varchar(20) primary key,G_price integer,stock integer,foodImage blob)"
        val CREATE_ORDERITEM =
            "create table OrderItem(`order` Integer primary key autoincrement,M_id varchar(20),goodsName varchar(20),G_price integer,seatNo integer)"
        val CREATE_SEAT =
            "create table Seat(seatNo Integer primary key, M_id varchar(20))"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //테이블 접근 및 생성
        db?.execSQL(CREATE_MEMBER)
        db?.execSQL(CREATE_GOODS)
        db?.execSQL(CREATE_ORDERITEM)
        db?.execSQL(CREATE_SEAT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //테이블에 변경사항이 있을 경우 호출됨
        //sqliteHelper()의 생성자를 호출할 때 기존 데이터베이스와 version을 비교해서 높으면 호출된다.
    }

    //멤버데이터 입력함수
    fun insertMemberData(member : MemberData) {
        //db 가져오기
        val wd = writableDatabase

        //MemberData를 입력타입으로 변환
        val values = ContentValues()
        values.put("M_id",member.M_id)
        values.put("M_password",member.M_password)
        values.put("name",member.name)
        values.put("phoneNo",member.phoneNo)
        values.put("time",60)
        Log.d(LOG_LOGIN,"values ${values}")

        //db에 넣기
        wd.insert("Member",null,values)

        //db 닫기
        wd.close()
    }

    //멤버데이터 확인함수
    @SuppressLint("Range")
    fun checkMemberData(M_id: String, M_password: String) :  Boolean{

        //아이디와 비밀번호가 맞는 투플 검색
        val select = "select * from Member where M_id = '${M_id}' and M_password = '${M_password}'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)

        //회원정보에 맞는 투플이 없을 시 false 리턴
        Log.d(LOG_LOGIN,"cursor = ${cursor}, cursor.count = ${cursor.count}")
        if(cursor.count <= 0){
            cursor.close()
            rd.close()
            return false
        }

        //회원정보 임시저장
        if(cursor.moveToFirst()){
            U_id = cursor.getString(cursor.getColumnIndex("M_id"))
            U_name = cursor.getString(cursor.getColumnIndex("name"))
            U_time = cursor.getInt(cursor.getColumnIndex("time"))
        }

        //db 닫기
        cursor.close()
        rd.close()
        return true
    }

    //리셋함수
    fun reset(){
        val wd = writableDatabase
        val delete = "delete from Member"
        wd.execSQL(delete)

        wd.close()
    }

    //상품데이터 입력함수
    fun insertGoodsData(goodsData : GoodsData){
        val wd = writableDatabase
        val values = ContentValues()

        values.put("goodsName",goodsData.goodsName)
        values.put("G_price",goodsData.G_price)
        values.put("stock",goodsData.stock)
        values.put("foodImage",goodsData.foodImage)
        Log.d(LOG_FOOD,"values ${values}")

        wd.insert("Goods",null,values)

        wd.close()
    }

    @SuppressLint("Range")
    fun presentGoodsData() : MutableList<GoodsData>{

        //상품 검색
        val list = mutableListOf<GoodsData>()
        val select = "select * from Goods"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)

        Log.d(LOG_FOOD,"cursor = ${cursor}, cursor.count = ${cursor.count}")

        //상품정보 갖고오기
        while(cursor.moveToNext()) {
            val goodsName =cursor.getString(cursor.getColumnIndex("goodsName"))
            val G_price =cursor.getInt(cursor.getColumnIndex("G_price"))
            val stock =cursor.getInt(cursor.getColumnIndex("stock"))
            val foodImage : ByteArray? = cursor.getBlob(cursor.getColumnIndex("foodImage"))?: null


            val tempGoodsData = GoodsData(goodsName,G_price,stock,foodImage)
            list.add(tempGoodsData)
        }

        //db 닫기
        cursor.close()
        rd.close()

        return list
    }

    fun insertOrderItem(orderData : OrderData){
        val wd = writableDatabase
        val values = ContentValues()

        values.put("M_id", U_id)
        values.put("goodsName",orderData.goodsName)
        values.put("G_price",selectPrice(orderData.goodsName))
        values.put("seatNo",1)
        Log.d(LOG_ORDER,"values : ${values}")

        wd.insert("OrderItem",null,values)

        wd.close()
    }

    @SuppressLint("Range")
    fun presentCustemerOrder() : MutableList<OrderData>{
        //상품 검색
        val list = mutableListOf<OrderData>()
        val select = "select * from OrderItem where M_id = '${U_id}'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)

        Log.d(LOG_ORDER,"cursor = ${cursor}, cursor.count = ${cursor.count}")

        //상품정보 갖고오기
        while(cursor.moveToNext()) {
            val M_id = cursor.getString(cursor.getColumnIndex("M_id"))
            val goodsName =cursor.getString(cursor.getColumnIndex("goodsName"))
            val G_price = cursor.getInt(cursor.getColumnIndex("G_price"))
            val seatNo = cursor.getInt(cursor.getColumnIndex("seatNo"))

            val tempGoodsData = OrderData(M_id,goodsName,G_price,seatNo)
            list.add(tempGoodsData)
        }

        //db 닫기
        cursor.close()
        rd.close()

        return list
    }

    @SuppressLint("Range")
    private fun selectPrice(goodsName : String) : Int{
        val select = "select G_price from Goods where goodsName = '${goodsName}'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)
        var G_price : Int = 1

        Log.d("log_price","cursor = ${cursor}, cursor.count = ${cursor.count}")

        //상품정보 갖고오기
        if(cursor.moveToFirst()) {
            G_price = cursor.getInt(cursor.getColumnIndex("G_price"))
            Log.d("log_price","G_price : ${G_price}")
            return G_price
        }

        //db 닫기
        cursor.close()
        rd.close()

        return G_price
    }
}