package com.example.androidwithsql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.androidwithsql.MainActivity.Companion.LOG_LOGIN


class SqliteHelper(context: Context,name: String,version:Int) : SQLiteOpenHelper(context, name, null,version) {

    //로그인 시 회원 아이디 및 이름,시간 임시저장
    companion object{
        var U_id : String? = null
        var U_name : String? = null
        var U_time : Int = 0
    }

    override fun onCreate(db: SQLiteDatabase?) {

        //테이블 접근 및 생성
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
        Log.d(LOG_LOGIN,"values ${values}")

        //db에 넣기
        wd.insert("Member",null,values)

        //db 닫기
        wd.close()
    }

    //데이터 확인함수
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

}