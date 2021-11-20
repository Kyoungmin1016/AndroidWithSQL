package com.example.androidwithsql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.example.androidwithsql.MainActivity.Companion.LOGINLOG
import java.lang.reflect.Member

data class MemberData(var M_id : String, var M_password: String, var name: String, var phoneNo: String)
data class UsingMember(var M_id : String, var name: String, var time : Int)

class SqliteHelper(context: Context,name: String,version:Int) : SQLiteOpenHelper(context, name, null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        //테이블 접근 및 생성성
        val create =
            "create table Member(M_id varchar(20) primary key,M_password varchar(20),name varchar(20),phoneNo varchar(11),time integer)"
        db?.execSQL(create)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //테이블에 변경사항이 있을 경우 호출됨
        //sqliteHelper()의 생성자를 호출할 때 기존 데이터베이스와 version을 비교해서 높으면 호출된다.
    }

    //데이터 입력함수
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
        Log.d("log_login","values ${values}")

        //db에 넣기
        wd.insert("Member",null,values)

        //db 닫기
        wd.close()
    }

    //데이터 확인함수
    fun checkMemberData(M_id: String, M_password: String) :  Boolean{

        val select = "select * from Member where M_id = '${M_id}' and M_password = '${M_password}'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(select,null)

        Log.d("log_login","cursor = ${cursor}, cursor.count = ${cursor.count}")
        if(cursor.count <= 0){
            cursor.close()
            rd.close()
            return false
        }

        //회원정보 인텐트
        if(cursor.moveToFirst()){
            val UM = UsingMember(
                cursor.getString(cursor.getColumnIndex("M_id")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getInt(cursor.getColumnIndex("time"))
            )
            Log.d(LOGINLOG,"UsingMember : ${UM}")
            
        }
        cursor.close()
        rd.close()
        return true
    }

    fun reset(){
        val wd = writableDatabase
        val delete = "delete from Member"
        wd.execSQL(delete)

        wd.close()
    }

}