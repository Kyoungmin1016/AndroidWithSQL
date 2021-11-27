package com.example.androidwithsql

data class MemberData(val M_id : String, val M_password: String, val name: String, val phoneNo: String)

data class GoodsData(val goodsName:String,val G_price : Int,val stock : Int, val foodImage : ByteArray?)

data class OrderData(val M_id:String,val goodsName:String,val G_price : Int,val seatNo:Int?)

//data class OrderData(val goodsName : String)