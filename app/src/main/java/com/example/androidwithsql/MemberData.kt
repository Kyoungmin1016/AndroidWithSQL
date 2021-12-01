package com.example.androidwithsql


data class SeatData( val imageResource: Int, val seatNo: Int, var M_id: String)

data class MemberData(val M_id : String, val M_password: String, val name: String, val phoneNo: String?, val time : Int)

data class GoodsData(val goodsName:String,val G_price : Int,val stock : Int, val foodImage : ByteArray?)

data class OrderData(val orderNo : Int?,val M_id:String, val goodsName:String, val G_price: Int?, val seatNo:Int?)

data class TimeData(val time: Int, val T_price: Int)

//data class OrderData(val goodsName : String)
